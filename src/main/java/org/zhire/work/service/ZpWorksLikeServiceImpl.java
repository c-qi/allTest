package org.zhire.work.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.entity.works.weixin.ZpWeiXin;
import org.zhire.work.entity.works.zuopin.ZpWorks;
import org.zhire.work.entity.works.zuopin.ZpWorksLike;
import org.zhire.work.es.ZpCommentRepository;
import org.zhire.work.es.ZpLikeRepository;
import org.zhire.work.es.v1.user.ZpUserRepository;
import org.zhire.work.es.zuopin.ZpWorksRepository;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.vo.ZpWorksLikeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ZpWorksLikeServiceImpl implements ZpWorksLikeService {

    @Autowired
    private ZpLikeRepository zpWorksLikeRepository;

    @Autowired
    private ZpUserRepository zpUserRepository;

    @Autowired
    private ZpCommentRepository zpWorksRepository;

    @Autowired
    private ZpWorksRepository zpWorksy;

    /**
     * 点赞
     *
     * @param worksId
     * @param worksLikeUserId
     * @param userType
     * @param openId
     * @param gzhType
     * @param unionid
     * @param flag
     * @return
     */
    @Override
    public ZpWorksLike likeWork(Long worksId, Long worksUserId, Long worksLikeUserId, ZpWorksLike.LIKE_USER_TYPE userType, String openId, ZpWeiXin.GZHTYPE gzhType, String unionid, String flag) {
        ZpWorksLike zpWorksLike = new ZpWorksLike();
        zpWorksLike.setWorksId(worksId);
        zpWorksLike.setWorksLikeUserId(worksLikeUserId);
        zpWorksLike.setUserType(userType);
        zpWorksLike.setOpenid(openId);
        zpWorksLike.setGzhType(gzhType);
        zpWorksLike.setUnionid(unionid);
        if (StringUtils.isBlank(flag)) {
            zpWorksLike.setDel(0L);
        } else {
            zpWorksLike.setDel(1L);
        }
        log.info("点赞操作入参：{}", JSON.toJSONString(zpWorksLike));
        // 点赞之前先判断是否存在数据
        Optional<ZpWorksLike> worksLike = zpWorksLikeRepository.findFirstByWorksIdAndWorksLikeUserId(worksId, worksLikeUserId);
        if (worksLike.isPresent()) {
            ZpWorksLike like = worksLike.get();
            if (like.getDel() == 0L) {
                // 取消点赞后用户表点赞数-1
                saveCount(worksUserId, -1L);
                // 取消点赞后作品表点赞数量-1
                saveWorksLikeCount(worksId, -1);
            } else {
                // 再次点赞后用户表点赞数+1
                saveCount(worksUserId, 1L);
                // 取消点赞后作品表点赞数量+1
                saveWorksLikeCount(worksId, 1);
            }
            like.setDel(zpWorksLike.getDel());
            ZpWorksLike save = zpWorksLikeRepository.save(like);
            log.info("点赞操作结果：{}", save);
            return save;
        } else {
            // 第一次点赞后用户表点赞数+1
            saveCount(worksUserId, 1L);
            // 取消点赞后作品表点赞数量+1
            saveWorksLikeCount(worksId, 1);
            ZpWorksLike save = zpWorksLikeRepository.save(zpWorksLike);
            log.info("第一次点赞结果：{}", save);
            return save;
        }
    }

    /**
     * 更新用户表点赞数
     *
     * @param userId 评论用户的ID
     */
    private void saveCount(Long userId, Long num) {
        Optional<ZpUser> userBusiness = zpUserRepository.findById(userId);
        if (userBusiness.isPresent()) {
            ZpUser zpUser = userBusiness.get();
            Long count = zpUser.getLikeCount();
            zpUser.setLikeCount(count + num);
            ZpUser save = zpUserRepository.save(zpUser);
            log.info("更新用户点赞数量结果：{}", save);
        }
    }

    /**
     * 更新作品表点赞数
     *
     * @param worksId 作品ID
     */
    private void saveWorksLikeCount(Long worksId, Integer num) {
        Optional<ZpWorks> works = zpWorksy.findById(worksId);
        if (works.isPresent()) {
            ZpWorks zpWorks = works.get();
            Integer count = zpWorks.getLikeCount();
            // 如果是第一次点赞，设置首次点赞时间
            if (count == 0) {
                zpWorks.setLikeFirstTime(System.currentTimeMillis());
            }
            zpWorks.setLikeCount(count + num);
            ZpWorks save = zpWorksy.save(zpWorks);
            log.info("更新用户点赞数量结果：{}", save);
        }
    }



    /**
     * 取消点赞
     *
     * @param worksId
     * @param worksLikeUserId
     * @param userType
     * @param openId
     * @param gzhType
     * @param unionid
     * @return
     */
    @Override
    public ZpWorksLike cancleLike(Long worksId, Long worksUserId, Long worksLikeUserId, ZpWorksLike.LIKE_USER_TYPE userType, String openId, ZpWeiXin.GZHTYPE gzhType, String unionid) {
        return likeWork(worksId, worksUserId, worksLikeUserId, userType, openId, gzhType, unionid, "cancle");
    }

    /**
     * 根据作品ID获取点赞列表
     *
     * @param worksId
     * @return
     */
    @Override
    public ResponsePage.Page<ZpWorksLikeResponse> getWorkDetailsLike(Long worksId) {

        List<ZpWorksLikeResponse> list = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC, "ctime");
        List<ZpWorksLike> likes = zpWorksLikeRepository.findFirst10ByWorksId(worksId, sort);
        Long count = zpWorksLikeRepository.countByWorksId(worksId);
        System.out.println("count:" + count);
        System.out.println("like:" + likes);
        likes.forEach(
                l -> {
                    Optional<ZpUser> repository = zpUserRepository.findById(l.getWorksLikeUserId());
                    if (repository.isPresent()) {
                        ZpWorksLikeResponse zplikeRep = new ZpWorksLikeResponse();
                        System.out.println("head:{}"+repository.get().getHead());
                        System.out.println("username:{}"+repository.get().getUsername());
                        zplikeRep.setHead(repository.get().getHead());
                        zplikeRep.setUsername(repository.get().getUsername());
                        list.add(zplikeRep);
                    }
                }
        );


        // List<Map<String, Object>> allWorksLike = zpWorksLikeRepository.findAllWorksLikeByWorksId(worksId);
        //  List<ZpWorksLikeResponse> likeResponses = JSON.parseArray(JSON.toJSONString(allWorksLike), ZpWorksLikeResponse.class);
        // log.info("根据作品ID获取点赞列表出参：{}", JSON.toJSONString(likeResponses));
        // return likeResponses;
        ResponsePage.Page<ZpWorksLikeResponse> result = new ResponsePage.Page<>();
//        int pageNum = (pageNumber - 1) * pageSize;
//        List<Map<String, Object>> maps = zpWorksCommentRepository.findWorksCommentByPage(worksId, pageNum, pageSize);
//        List<ZpWorksCommentResponse> worksCommentResponseList = JSON.parseArray(JSON.toJSONString(maps), ZpWorksCommentResponse.class);
        result.setContent(list)
                .setTotalElements(count);
        log.info("点赞列表出参：{}", JSON.toJSONString(result));
        return result;
    }
}