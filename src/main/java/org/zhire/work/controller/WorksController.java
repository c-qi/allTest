package org.zhire.work.controller;

import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhire.work.entity.works.weixin.ZpWeiXin;
import org.zhire.work.entity.works.zuopin.ZpWorksComment;
import org.zhire.work.entity.works.zuopin.ZpWorksLike;
import org.zhire.work.model.Response;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.vo.ZpWorksCommentResponse;
import org.zhire.work.model.vo.ZpWorksLikeResponse;
import org.zhire.work.service.ZpWorksCommentService;
import org.zhire.work.service.ZpWorksLikeService;

/**
 */
@Slf4j
@RestController
@RequestMapping("/works")
@Api(name = "WorksController", description = "作品业务")
public class WorksController {


    @Autowired
    private ZpWorksCommentService zpWorksCommentService;

    @Autowired
    private ZpWorksLikeService zpWorksLikeService;


    @ApiMethod(description = "评论作品")
    @PostMapping("/commentWork")
    public Response<ZpWorksComment> commentWork(
            @ApiQueryParam(name = "worksId", description = "作品id")
            @RequestParam(required = true) Long worksId,
            @ApiQueryParam(name = "worksUserId", description = "作品作者id") @RequestParam(required = true) Long worksUserId,
            @ApiQueryParam(name = "worksCommentUserType", description = "0：DEFAULT 默认 1：TEACHER 老师点评 2：STUDENT 学生评论")
            @RequestParam(required = true) ZpWorksComment.COMMENT_USER_TYPE worksCommentUserType,
            @ApiQueryParam(name = "worksCommentUserId", description = "评论人id（worksuserId）")
            @RequestParam(required = true) Long worksCommentUserId,
            @ApiQueryParam(name = "worksVideoContent", description = "语音评论内容")
            @RequestParam(required = false) String worksVideoContent,
            @ApiQueryParam(name = "soundCommentSecond", description = "作业语音点评秒")
            @RequestParam(required = false) String soundCommentSecond,
            @ApiQueryParam(name = "worksWordsContent", description = "文字评论内容")
            @RequestParam(required = false) String worksWordsContent) {
        try {
            log.info("作品评论入参，作品ID:{}，作品作者ID：{}，评论类型：{}，评论人ID：{}，语音评论内容：{}，语音点评秒：{}，文字评论内容：{}",
                    worksId, worksUserId, worksCommentUserType, worksCommentUserId, worksVideoContent, soundCommentSecond, worksWordsContent);
            ZpWorksComment worksComment = new ZpWorksComment();
            worksComment.setWorksId(worksId)
                    .setWorksCommentUserType(worksCommentUserType)
                    .setWorksCommentUserId(worksCommentUserId)
                    .setWorksVideoContent(worksVideoContent)
                    .setSoundCommentSecond(soundCommentSecond)
                    .setWorksWordsContent(worksWordsContent);
            return Response.ok(zpWorksCommentService.saveWorksComment(worksComment, worksUserId));
        } catch (Exception e) {
            log.info("评论失败：{}", e);
            return Response.exception("评论失败");
        }
    }

    @ApiMethod(description = "根据作品ID分页获取作品评论信息")
    @GetMapping("/getWorkDetailsComment")
    public Response<ResponsePage.Page<ZpWorksCommentResponse>> getWorkDetailsComment(
            @ApiQueryParam(name = "worksId", description = "作品id") @RequestParam(required = true) Long worksId,
            @ApiQueryParam(name = "pageNumber", description = "页码") @RequestParam(required = true) Integer pageNumber,
            @ApiQueryParam(name = "pageSize", description = "条数") @RequestParam(required = true) Integer pageSize
    ) {
        try {
            log.info("根据作品ID分页获取作品评论信息入参：作品ID：{}，页码：{}，条数：{}", worksId, pageNumber, pageSize);
            return Response.ok(zpWorksCommentService.queryWorksAllComment(worksId, pageNumber, pageSize));
        } catch (Exception e) {
            log.info("获取分页评论失败：{}", e);
            return Response.exception(e);
        }
    }


    @ApiMethod(description = "点赞")
    @PostMapping("/likeWork")
    public Response<ZpWorksLike> likeWork(
            @ApiQueryParam(name = "worksId", description = "作品id") @RequestParam(required = true) Long worksId,
            @ApiQueryParam(name = "worksUserId", description = "作品作者id") @RequestParam(required = true) Long worksUserId,
            @ApiQueryParam(name = "worksLikeUserId", description = "点赞的用户id") @RequestParam(required = true) Long worksLikeUserId,
            @ApiQueryParam(name = "userType", description = "用户类型（0:默认DEFAULT 1:app用户APP_USER 2:微信用户WEIXIN_USER）") @RequestParam(required = false) ZpWorksLike.LIKE_USER_TYPE userType,
            @ApiQueryParam(name = "openId", description = "openId") @RequestParam(required = false) String openId,
            @ApiQueryParam(name = "gzhType", description = "公众号标识") @RequestParam(required = false) ZpWeiXin.GZHTYPE gzhType,
            @ApiQueryParam(name = "unionid", description = "unionid") @RequestParam(required = false) String unionid,
            @RequestParam(required = false) String flag
    ) {
        try {
            log.info("点赞入参：作品ID：{}，作品作者id：{}，点赞的用户ID：{}，用户类型：{}，openID：{}，公众号标识：{}，unionid{}",
                    worksId, worksUserId, worksLikeUserId, userType, openId, gzhType, unionid);
            return Response.ok(zpWorksLikeService.likeWork(worksId, worksUserId, worksLikeUserId, userType, openId, gzhType, unionid, flag));
        } catch (Exception e) {
            log.info("点赞失败：{}", e);
            return Response.exception(e);
        }
    }

    @ApiMethod(description = "取消点赞")
    @PostMapping("/likeCancleWork")
    public Response cancleLikeWork(
            @ApiQueryParam(name = "worksId", description = "作品id") @RequestParam(required = true) Long worksId,
            @ApiQueryParam(name = "worksUserId", description = "作品作者id") @RequestParam(required = true) Long worksUserId,
            @ApiQueryParam(name = "worksLikeUserId", description = "用户id（worksUserId") @RequestParam(required = true) Long worksLikeUserId,
            @ApiQueryParam(name = "userType", description = "用户类型（0:默认DEFAULT 1:app用户APP_USER 2:微信用户WEIXIN_USER）") @RequestParam(required = false) ZpWorksLike.LIKE_USER_TYPE userType,
            @ApiQueryParam(name = "openId", description = "openId") @RequestParam(required = false) String openId,
            @ApiQueryParam(name = "gzhType", description = "公众号标识") @RequestParam(required = false) ZpWeiXin.GZHTYPE gzhType,
            @ApiQueryParam(name = "unionid", description = "unionid") @RequestParam(required = false) String unionid
    ) {
        try {
            log.info("点赞入参：作品ID：{}，作品作者id：{}，点赞的用户ID：{}，用户类型：{}，openID：{}，公众号标识：{}，unionid{}",
                    worksId, worksUserId, worksLikeUserId, userType, openId, gzhType, unionid);
            return Response.ok(zpWorksLikeService.cancleLike(worksId, worksUserId, worksLikeUserId, userType, openId, gzhType, unionid));
        } catch (Exception e) {
            log.info("取消点赞失败：{}", e);
            return Response.exception(e);
        }
    }

    @ApiMethod(description = "根据作品ID获取作品点赞列表")
    @GetMapping("/getWorkDetailsLike")
    public Response<ResponsePage.Page<ZpWorksLikeResponse>> getWorkDetailsLike(
            @ApiQueryParam(name = "worksId", description = "作品id") @RequestParam(required = true) Long worksId
    ) {
        try {
            log.info("根据作品ID获取作品点赞列表入参：{}", worksId);
            return Response.ok(zpWorksLikeService.getWorkDetailsLike(worksId));
        } catch (Exception e) {
            log.info("获取点赞列表失败：{}", e);
            return Response.exception(e);
        }
    }

}
