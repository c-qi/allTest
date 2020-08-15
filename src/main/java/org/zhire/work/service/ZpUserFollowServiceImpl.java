package org.zhire.work.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZpUserFollowServiceImpl implements ZpUserFollowService {

    @Autowired
    private ZpUserFollowRepository zpUserFollowRepository;

    @Autowired
    ZpUserRepository zpUserRepository;

    private final List<ZpUserFollow.FOLLOWTYPE> TYPELIST = Lists.newArrayList(ZpUserFollow.FOLLOWTYPE.FOLLOWED, ZpUserFollow.FOLLOWTYPE.BOTH_FOLLOWED);


    /**
     * 查询用户的关注列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<ZpUserFollow> findAllByUserId(Long userId) {
        return zpUserFollowRepository.findAllByUserId(userId);
    }

    /**
     * 查询用户的关注用户ID
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> getAllMyFollowUserIds(Long userId) {
        List<ZpUserFollow> userFollow = zpUserFollowRepository.findAllByUserIdAndFollowTypeIn(userId, TYPELIST);
        return userFollow.stream().map(ZpUserFollow::getFollowUserId).distinct().collect(Collectors.toList());
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
    private ZpUserFollow getFollowUserInfo(ZpUserFollow user, Long followUserId, Long userId, String username, String followUsername) {
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
            userFollowed.setUsername(username);
            userFollowed.setFollowUserId(userId);
            userFollowed.setFollowUsername(followUsername);
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
    public ZpUserFollow follow(Long userId, String username, Long followUserId, String followUsername) {
        // 插入之前先判断是否存在数据
        Optional<ZpUserFollow> userType = zpUserFollowRepository.findByUserIdAndFollowUserId(userId, followUserId);
        if (userType.isPresent()) {
            ZpUserFollow userFollow = userType.get();
            log.info("插入之前判断存在数据：{}", JSON.toJSONString(userFollow));
            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
            userFollow.setLatestTime(System.currentTimeMillis());
            zpUserFollowRepository.save(userFollow);
            log.info("更新用户的关注状态为已关注成功，开始更新被关注用户粉丝数");
            saveFollowUserFollowCount(followUserId);
            log.info("开始查看被关注用户的关注情况");
            return getFollowUserInfo(userFollow, followUserId, userId, username, followUsername);
        } else {
            ZpUserFollow userFollow = new ZpUserFollow();
            userFollow.setUserId(userId);
            userFollow.setUsername(username);
            userFollow.setFollowUserId(followUserId);
            userFollow.setFollowUsername(followUsername);
            userFollow.setFollowType(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
            // A -> B 已关注
            userFollow.setLatestTime(System.currentTimeMillis());
            ZpUserFollow save = zpUserFollowRepository.save(userFollow);
            log.info("第一次关注设置状态为已关注成功,开始更新被关注用户的粉丝数+1");
            saveFollowUserFollowCount(followUserId);
            // 查询B对于A的关注状态
            return getFollowUserInfo(save, followUserId, userId, username, followUsername);
        }

    }

    /**
     * 取关
     *
     * @param userId
     * @param unfollowUserId
     * @return
     */
    @Override
    @Transactional
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
        Page<ZpUserFollow> zpUserFollows = zpUserFollowRepository.findByFollowUserIdAndFollowTypeIn(userId, TYPELIST, pageable);
        zpUserFollows.forEach(
                l -> {
                    Optional<ZpUser> user = zpUserRepository.findById(l.getFollowUserId());
                    if (user.isPresent()) {
                        ZpUser zpUser = user.get();
                        list.addAll(getReponseList(zpUser));
                    }
                }
        );
        ResponsePage.Page<ZpFollowResponse> result = new ResponsePage.Page<>();
        long count = zpUserFollowRepository.countByUserIdAndFollowTypeIn(userId, TYPELIST);
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
        List<ZpFollowResponse> list = Lists.newArrayList();
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
        // 获取粉丝列表
        Page<ZpUserFollow> zpUserFollows = zpUserFollowRepository.findByFollowUserIdAndFollowTypeIn(userId, TYPELIST, pageable);
        zpUserFollows.forEach(
                l -> {
                    Optional<ZpUser> user = zpUserRepository.findById(l.getUserId());
                    if (user.isPresent()) {
                        ZpUser zpUser = user.get();
                        list.addAll(getReponseList(zpUser));
                    }
                }
        );
        ResponsePage.Page<ZpFollowResponse> result = new ResponsePage.Page<>();
        long count = zpUserFollowRepository.countByUserIdAndFollowTypeIn(userId, TYPELIST);
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
     * 根据名称模糊搜索我的关注用户或者根据ID匹配
     *
     * @param userId
     * @param followUserId
     * @param followUsername
     * @return
     */
    @Override
    public List<ZpFollowResponse> getMyFollowByUsername(Long userId, Long followUserId, String followUsername) {
        ArrayList<ZpFollowResponse> list = Lists.newArrayList();
        ZpFollowResponse response = new ZpFollowResponse();
        // ID不为空按ID匹配查询
        if (StringUtils.isNotEmpty(null==followUserId?"":followUserId.toString()) && StringUtils.isEmpty(followUsername)) {
            Optional<ZpUserFollow> zpUserFollow = zpUserFollowRepository.findByUserIdAndFollowUserIdAndFollowTypeIn(userId, followUserId, TYPELIST);
            if (zpUserFollow.isPresent()) {
                Optional<ZpUser> user = zpUserRepository.findById(followUserId);
                if (user.isPresent()) {
                    ZpUser zpUser = user.get();
                    list.addAll(getReponseList(zpUser));
                }
            }
        } else if (StringUtils.isEmpty(null==followUserId?"":followUserId.toString()) && StringUtils.isNotEmpty(followUsername)) {
            // 名称不为空按名称模糊查询
            List<ZpUserFollow> userFollows = zpUserFollowRepository.findByUserIdAndFollowUsernameLikeAndFollowTypeIn(userId, followUsername, TYPELIST);
            userFollows.forEach(l -> {
                Optional<ZpUser> userFollow = zpUserRepository.findById(l.getFollowUserId());
                if (userFollow.isPresent()) {
                    ZpUser zpUser = userFollow.get();
                    list.addAll(getReponseList(zpUser));
                }
            });
        }
        return list;
    }

    /**
     * 根据名称模糊搜索我的粉丝用户或者根据ID匹配
     *
     * @param userId
     * @param fansUserId
     * @param fansUsername
     * @return
     */
    @Override
    public List<ZpFollowResponse> getMyFansByUsernameOrId(Long userId, Long fansUserId, String fansUsername) {
        ArrayList<ZpFollowResponse> list = Lists.newArrayList();
        // ID不为空按ID匹配查询
        if (StringUtils.isNotEmpty(fansUserId==null?"":fansUserId.toString()) && StringUtils.isEmpty(fansUsername)) {
            Optional<ZpUserFollow> zpUserFollow = zpUserFollowRepository.findByFollowUserIdAndUserIdAndFollowTypeIn(userId, fansUserId, TYPELIST);
            if (zpUserFollow.isPresent()) {
                Optional<ZpUser> user = zpUserRepository.findById(fansUserId);
                if (user.isPresent()) {
                    ZpUser zpUser = user.get();
                    list.addAll(getReponseList(zpUser));
                }
            }
        } else if (StringUtils.isEmpty(fansUserId==null?"":fansUserId.toString()) && StringUtils.isNotEmpty(fansUsername)) {
            // 名称不为空按名称模糊查询
            List<ZpUserFollow> userFollows = zpUserFollowRepository.findByFollowUserIdAndUsernameLikeAndFollowTypeIn(userId, fansUsername, TYPELIST);
            userFollows.forEach(l -> {
                Optional<ZpUser> userFollow = zpUserRepository.findById(l.getUserId());
                if (userFollow.isPresent()) {
                    ZpUser zpUser = userFollow.get();
                    list.addAll(getReponseList(zpUser));
                }
            });
        }
        return list;
    }

    /**
     * 修改关注表的用户名称
     *
     * @param userId
     * @param username
     */
    @Override
    public void updateFollowUsernameByUserId(Long userId, String username) {
        try {
            // 修改userId是关注用户的名称
            int i = zpUserFollowRepository.updateUsernameByUserId(userId, username);
            log.info("修改userId是关注用户的名称结果：{}", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 修改userId是被关注者用户的姓名
            int j = zpUserFollowRepository.updateFollowUsernameByFollowUserId(userId, username);
            log.info("修改userId是被关注者用户的姓名结果：{}", j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 共用代码抽取
     *
     * @param zpUser
     * @return
     */
    private List<ZpFollowResponse> getReponseList(ZpUser zpUser) {
        ArrayList<ZpFollowResponse> list = Lists.newArrayList();
        ZpFollowResponse response = new ZpFollowResponse();
        BeanUtils.copyProperties(zpUser, response);
        response.setAge(getAge(zpUser.getBirthday()));
        response.setFollowtype(ZpUserFollow.FOLLOWTYPE.FOLLOWED);
        list.add(response);
        return list;
    }

    /**
     * 根据生日获取年龄
     *
     * @param time
     * @return
     */
    private long getAge(long time) {
        int age = 0;
        try {
            Date date = new Date(time);
            int ageByBirth = ToolUtils.getAgeByBirth(date);
            age = ageByBirth;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("根据生日转换年龄失败：{}", e);
        }
        return age;
    }
}
