package org.zhire.work.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.entity.works.user.ZpUserFollow;
import org.zhire.work.es.v1.user.ZpUserFollowRepository;
import org.zhire.work.es.v1.user.ZpUserRepository;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.ZpFollowResponse;
import org.zhire.work.model.ZphdResponse;

import java.util.*;

@Slf4j
@Service
public class WorkUserServiceImpl implements WorkUserService {

    @Autowired
    ZpUserRepository zpUserRepository;

    @Autowired
    private ZpUserFollowRepository zpUserFollowRepository;


    /**
     * 获取用户信息 map
     *
     * @param ids
     * @return
     */
    @Override
    public Optional<Map<Long, ZpUser>> findAllByIdsOfMap(List<Long> ids) {
        Map<Long, ZpUser> map = findAllByIds(ids)
                .map(this::buildMap)
                .orElse(Collections.EMPTY_MAP);
        return Optional.of(map);
    }

    @Override
    public Optional<List<ZpUser>> findAllByIds(List<Long> ids) {
        return null;
    }


    /**
     * 更新被关注者的粉丝数
     *
     * @param followUserId
     */
    private void saveFollowUserFollowCount(Long followUserId) {
        Optional<ZpUser> user = zpUserRepository.findById(followUserId);
        if (user.isPresent()) {
            ZpUser zpUser = user.get();
            Long count = zpUser.getFollowCount();
            zpUser.setFollowCount(count + 1);
            zpUserRepository.save(zpUser);
            log.info("更新被关注用户的粉丝状态成功");
        }
    }

    /**
     * 查看被关注用户对于关注用户的关注情况
     *
     * @param user
     * @param followUserId
     * @param userId
     * @return
     */
    private ZpUserFollow getFollowUserInfo(ZpUserFollow user, Long followUserId, Long userId) {
        Optional<ZpUserFollow> type = zpUserFollowRepository.findByUserIdAndFollowUserId(followUserId, userId);
        if (type.isPresent()) {
            ZpUserFollow zpUserFollow = type.get();
            log.info("存在被关注用户的数据：{}", JSON.toJSONString(zpUserFollow));
            ZpUserFollow.FOLLOWTYPE followType = zpUserFollow.getFollowType();
            // 如果 B A 未关注 设置为回关
            if (followType.equals(ZpUserFollow.FOLLOWTYPE.DEFAULT)) {
                zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
            } else if (followType.equals(ZpUserFollow.FOLLOWTYPE.FOLLOWED)) {
                // 如果 B A 已关注 设置为互关
                zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
                // A B 互关
                user.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
                zpUserFollowRepository.save(user);
            }
            return zpUserFollowRepository.save(zpUserFollow);
        } else {
            // 此时没查到数据表示 B未关注A 插入一条回关的状态数据 B -> A 回关
            ZpUserFollow userFollowed = new ZpUserFollow();
            userFollowed.setUserId(followUserId);
            userFollowed.setFollowUserId(userId);
            userFollowed.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
            return zpUserFollowRepository.save(userFollowed);
        }
    }

    /**
     * 关注
     *
     * @param userId
     * @param followUserId
     * @return
     */
    @Override
    @Transactional
    public ZpUserFollow follow(Long userId, Long followUserId) {
        // 插入之前先判断是否存在数据
        Optional<ZpUserFollow> userType = zpUserFollowRepository.findByUserIdAndFollowUserId(userId, followUserId);
        if (userType.isPresent()) {
            ZpUserFollow userFollow = userType.get();
            log.info("插入之前判断存在数据：{}", JSON.toJSONString(userFollow));
            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
            userFollow.setLatestTime(System.currentTimeMillis());
            zpUserFollowRepository.save(userFollow);
            log.info("更新用户的关注状态为已关注");
            // todo  更新B用户的被关注人数即粉丝人数
            saveFollowUserFollowCount(followUserId);
            log.info("开始查看被关注用户的关注情况");
            // todo
            return getFollowUserInfo(userFollow, followUserId, userId);
        } else {
            ZpUserFollow userFollow = new ZpUserFollow();
            userFollow.setUserId(userId);
            userFollow.setFollowUserId(followUserId);
            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
            // A -> B 已关注
            userFollow.setLatestTime(System.currentTimeMillis());
            ZpUserFollow save = zpUserFollowRepository.save(userFollow);
            log.info("第一次关注设置状态为已关注成功");
            saveFollowUserFollowCount(followUserId);
            // 查询B对于A的关注状态
            return getFollowUserInfo(save, followUserId, userId);
        }
    }

    private Map<Long, ZpUser> buildMap(List<ZpUser> list) {
        Map<Long, ZpUser> maps = new HashMap<>();
        list.forEach(zu -> maps.put(zu.getId(), zu));
        return maps;
    }


    public ZpUser saveUser(ZpUser user) {
        return zpUserRepository.save(user);
    }

    /**
     * 关注
     * A B 俩未互相关注用户
     * 1.A 关注 B  此时数据库
     * A B 已关注
     * 然后查 B 对于 A 的关注情况 此时无数据
     * 插入一条状态 B A 回关
     * <p>
     * 2.B 看到回关 点了关注后的处理
     * B A 互相关注
     * A B 互相关注
     * <p>
     * 3.B此时点了取消关注
     * 此时
     * B A 回关
     * A B 已关注
     * <p>
     * 4.此时A也把B取消关注
     * A B 未关注
     * B A 未关注
     *
     * @param userId
     * @param followUserId
     * @return
     */
    // @Override
//    public ZpUserFollow follow(Long userId, Long followUserId) {
//        // 插入之前先判断是否存在数据
//        Optional<ZpUserFollow> userType = zpUserFollowRepository.findByUserIdAndFollowUserId(userId, followUserId);
//        if (userType.isPresent()) {
//            ZpUserFollow userFollow = userType.get();
//            log.info("插入之前判断存在数据：{}", JSON.toJSONString(userFollow));
//            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
//            userFollow.setLatestTime(System.currentTimeMillis());
//            zpUserFollowRepository.save(userFollow);
//            log.info("更新用户的关注状态为已关注");
//            // 更新B用户的被关注人数即粉丝人数
//            Optional<ZpUser> user = zpUserRepository.findById(followUserId);
//            if (user.isPresent()) {
//                ZpUser zpUser = user.get();
//                Long count = zpUser.getFollowCount();
//                zpUser.setFollowCount(count + 1);
//                zpUserRepository.save(zpUser);
//                log.info("更新被关注用户的粉丝状态成功");
//            }
//            log.info("开始查看被关注用户的关注情况");
//            Optional<ZpUserFollow> type = zpUserFollowRepository.findByUserIdAndFollowUserId(followUserId, userId);
//            if (type.isPresent()) {
//                ZpUserFollow zpUserFollow = type.get();
//                log.info("存在被关注用户的数据：{}", JSON.toJSONString(zpUserFollow));
//                ZpUserFollow.FOLLOWTYPE followType = zpUserFollow.getFollowType();
//                // 如果 B A 未关注 设置为回关
//                if (followType.equals(ZpUserFollow.FOLLOWTYPE.DEFAULT)) {
//                    log.info("设置被关注用户状态为回关");
//                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
//                } else if (followType.equals(ZpUserFollow.FOLLOWTYPE.FOLLOWED)) {
//                    // 如果 B A 已关注 设置为互关
//                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
//                    // A B 互关
//                    userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
//                    zpUserFollowRepository.save(userFollow);
//                    log.info("设置双方关注状态为互关成功");
//                }
//                return zpUserFollowRepository.save(zpUserFollow);
//            } else {
//                // 此时没查到数据表示 B未关注A 插入一条回关的状态数据 B -> A 回关
//                ZpUserFollow userFollowed = new ZpUserFollow();
//                userFollowed.setUserId(followUserId);
//                userFollowed.setFollowUserId(userId);
//                userFollowed.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
//                log.info("没查到数据表示 设置被关注用户状态为回关成功");
//                return zpUserFollowRepository.save(userFollowed);
//            }
//        } else {
//            ZpUserFollow userFollow = new ZpUserFollow();
//            userFollow.setUserId(userId);
//            userFollow.setFollowUserId(followUserId);
//            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
//            // A -> B 已关注
//            userFollow.setLatestTime(System.currentTimeMillis());
//            ZpUserFollow save = zpUserFollowRepository.save(userFollow);
//            log.info("第一次关注设置状态为已关注成功");
//            // 更新B用户的被关注人数即粉丝人数
//            Optional<ZpUser> user = zpUserRepository.findById(followUserId);
//            if (user.isPresent()) {
//                ZpUser zpUser = user.get();
//                Long count = zpUser.getFollowCount();
//                zpUser.setFollowCount(count + 1);
//                zpUserRepository.save(zpUser);
//                log.info("第一次被关注用户的粉丝数量增加成功");
//            }
//            // 查询B对于A的关注状态
//            Optional<ZpUserFollow> type = zpUserFollowRepository.findByUserIdAndFollowUserId(followUserId, userId);
//            if (type.isPresent()) {
//                ZpUserFollow zpUserFollow = type.get();
//                log.info("用户第一次关注，查询被关注者状态成功：{}", JSON.toJSONString(zpUserFollow));
//                ZpUserFollow.FOLLOWTYPE followType = zpUserFollow.getFollowType();
//                // 如果 B A 未关注 设置为回关
//                if (followType.equals(ZpUserFollow.FOLLOWTYPE.DEFAULT)) {
//                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
//                } else if (followType.equals(ZpUserFollow.FOLLOWTYPE.FOLLOWED)) {
//                    // 如果 B A 已关注 设置为互关
//                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
//                    // A B 互关
//                    save.setFollowType(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
//                    zpUserFollowRepository.save(save);
//                }
//                return zpUserFollowRepository.save(zpUserFollow);
//
//            } else {
//                log.info("用户第一次关注，未查询被关注者状态，设置状态为回关");
//                // 此时没查到数据表示 B未关注A 插入一条回关的状态数据 B -> A 回关
//                ZpUserFollow userFollowed = new ZpUserFollow();
//                userFollowed.setUserId(followUserId);
//                userFollowed.setFollowUserId(userId);
//                userFollowed.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
//                return zpUserFollowRepository.save(userFollowed);
//            }
//        }
//
//    }

    /**
     * 取消关注
     *
     * @param userId
     * @param unfollowUserId
     * @return
     */
    @Override
    public ZpUserFollow unfollowUserId(Long userId, Long unfollowUserId) {
        Optional<ZpUserFollow> followUser = zpUserFollowRepository.findByUserIdAndFollowUserId(userId, unfollowUserId);
        if (followUser.isPresent()) {
            ZpUserFollow userFollow = followUser.get();
            // 设置 A B 未关注
            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.DEFAULT);
            zpUserFollowRepository.save(userFollow);
            // 更新A用户的被关注人数即粉丝人数
            Optional<ZpUser> user = zpUserRepository.findById(unfollowUserId);
            if (user.isPresent()) {
                ZpUser zpUser = user.get();
                Long count = zpUser.getFollowCount();
                zpUser.setFollowCount(count - 1);
                zpUserRepository.save(zpUser);
            }
            // 查询B对于A的关注状态
            Optional<ZpUserFollow> type = zpUserFollowRepository.findByUserIdAndFollowUserId(unfollowUserId, userId);
            if (type.isPresent()) {
                ZpUserFollow zpUserFollow = type.get();
                ZpUserFollow.FOLLOWTYPE followType = zpUserFollow.getFollowType();
                // 如果 B A 互相关注 设置B A 已关注, A B 回关
                if (followType.equals(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED)) {
                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
                    zpUserFollowRepository.save(zpUserFollow);
                    // 设置 A B 回关
                    userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
                    return zpUserFollowRepository.save(userFollow);
                } else if (followType.equals(ZpUserFollow.FOLLOWTYPE.FOLLOWED)) {
                    // 如果 B A 已关注 设置 A B 回关
                    userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED);
                    return zpUserFollowRepository.save(userFollow);
                } else if (followType.equals(ZpUserFollow.FOLLOWTYPE.BACK_FOLLOWED)) {
                    // 如果 B A 回关 设置 B A 未关注
                    zpUserFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.DEFAULT);
                    return zpUserFollowRepository.save(zpUserFollow);
                }

            }
        }
        return null;
    }

    /**
     * 用户的关注列表
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public ResponsePage.Page<ZpFollowResponse> getMyFollowList(Long userId, Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "latest_time");
        List<ZpFollowResponse> list = Lists.newArrayList();
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
        // 获取关注列表
        List<ZpUserFollow.FOLLOWTYPE> typeList = new ArrayList<>();
        typeList.add(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
        typeList.add(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);

        List<ZpUserFollow> zpUserFollows = zpUserFollowRepository.findAllByUserIdAndFollowTypeIn(userId, typeList);
        System.out.println("zpUserFollows: " + JSON.toJSONString(zpUserFollows));
        zpUserFollows.forEach(
                l -> {
                    ZpFollowResponse zf = new ZpFollowResponse();
                    Optional<ZpUser> user = zpUserRepository.findById(l.getFollowUserId());
                    if (user.isPresent()) {
                        ZpUser zpUser = user.get();
                        BeanUtils.copyProperties(zpUser, zf);
                        zf.setFollowtype(l.getFollowType());
                        zf.setAge(getAge(zpUser.getBirthday()));
                        list.add(zf);
                    }
                }
        );
        ResponsePage.Page<ZpFollowResponse> result = new ResponsePage.Page<>();
        long count = zpUserFollowRepository.countByUserIdAndFollowType(userId, ZpUserFollow.FOLLOWTYPE.FOLLOWED);
        long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        result.setContent(list)
                .setSize(pageSize)
                .setTotalElements(count)
                .setTotalPages(totalPage)
                .setNumber(pageNumber)
                .setEmpty(true);
        log.info("获取关注列表结果：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 获取我的粉丝列表
     *
     * @param userId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public ResponsePage.Page<ZpFollowResponse> getMyFansList(Long userId, Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "latest_time");
        List<ZpFollowResponse> list = Collections.emptyList();
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
        // 获取粉丝列表
        List<ZpUserFollow.FOLLOWTYPE> typeList = new ArrayList<>();
        typeList.add(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
        typeList.add(ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);
        Page<ZpUserFollow> zpUserFollows = zpUserFollowRepository.findByFollowUserIdAndFollowTypeIn(userId, typeList, pageable);
        zpUserFollows.forEach(
                l -> {
                    ZpFollowResponse zf = new ZpFollowResponse();
                    Optional<ZpUser> user = zpUserRepository.findById(l.getUserId());
                    if (user.isPresent()) {
                        ZpUser zpUser = user.get();
                        BeanUtils.copyProperties(zpUser, zf);
                        zf.setFollowtype(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
                        zf.setAge(getAge(zpUser.getBirthday()));
                        list.add(zf);
                    }
                }
        );
        ResponsePage.Page<ZpFollowResponse> result = new ResponsePage.Page<>();
        long count = zpUserFollowRepository.countByUserIdAndFollowType(userId, ZpUserFollow.FOLLOWTYPE.FOLLOWED);
        long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        result.setContent(list)
                .setSize(pageSize)
                .setTotalElements(count)
                .setTotalPages(totalPage)
                .setNumber(pageNumber)
                .setEmpty(true);
        log.info("获取关注列表结果：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public List<ZphdResponse> hd(Long userId) {

        List<Map<Object, Object>> list = zpUserFollowRepository.getHd(userId);
        List<ZphdResponse> zphdResponses = JSON.parseArray(JSON.toJSONString(list), ZphdResponse.class);
        System.out.println(list);
        return zphdResponses;
    }

    /**
     * 根据生日获取年龄
     *
     * @param time
     * @return
     */
    private long getAge(long time) {
        int age = 0;
        Date date = new Date(time);
        try {
            int ageByBirth = ToolUtils.getAgeByBirth(date);
            age = ageByBirth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return age;
    }

}
