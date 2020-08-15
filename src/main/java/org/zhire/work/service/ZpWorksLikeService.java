package org.zhire.work.service;

import org.zhire.work.entity.works.weixin.ZpWeiXin;
import org.zhire.work.entity.works.zuopin.ZpWorksLike;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.vo.ZpWorksLikeResponse;

public interface ZpWorksLikeService {

    ZpWorksLike likeWork(Long worksId, Long worksUserId, Long worksLikeUserId, ZpWorksLike.LIKE_USER_TYPE userType, String openId, ZpWeiXin.GZHTYPE gzhType, String unionid, String flag);

    ZpWorksLike cancleLike(Long worksId, Long worksUserId, Long worksLikeUserId, ZpWorksLike.LIKE_USER_TYPE userType, String openId, ZpWeiXin.GZHTYPE gzhType, String unionid);

    //List<ZpWorksLikeResponse> getWorkDetailsLike(Long worksId);
    ResponsePage.Page<ZpWorksLikeResponse> getWorkDetailsLike(Long worksId);
}
