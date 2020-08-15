package org.zhire.work.controller;

import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhire.utils.R;
import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.entity.works.user.ZpUserFollow;
import org.zhire.work.model.Response;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.ZpFollowResponse;
import org.zhire.work.model.ZphdResponse;
import org.zhire.work.service.WorkUserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author
 */
@Slf4j
@RestController
@RequestMapping("user")
@Api(name = "userController", description = "")
public class WorkUserController {

    @Autowired
    private WorkUserService userService;


    @ApiMethod(description = "获取用户信息 map")
    @GetMapping(value = "/findAllByIdsOfMap")
    public Response<Map<Long, ZpUser>> findAllByIdsOfMap(
            @ApiQueryParam(name = "userIds", description = "用户id 例：1,2,3")
            @RequestParam(required = true) String userIds){
        List<Long> collect = Arrays.stream(userIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return Response.ok(userService.findAllByIdsOfMap(collect));
    }

    @ApiMethod(description = "关注")
    @PostMapping("/follow")
    public Response<ZpUserFollow> likeWork(
            @ApiQueryParam(name = "userId", description = "用户id") @RequestParam(required = true) Long userId,
            @ApiQueryParam(name = "followUserId", description = "被关注的用户id") @RequestParam(required = true) Long followUserId
    ) {
        try {
            log.info("关注入参：用户id：{}，被关注的用户id：{}", userId, followUserId);
            return Response.ok(userService.follow(userId, followUserId));
        } catch (Exception e) {
            log.info("关注失败：{}", e);
            return Response.exception(e);
        }
    }

    @ApiMethod(description = "取消关注")
    @PostMapping("/unfollow")
    public Response<ZpUserFollow> unfollow(
            @ApiQueryParam(name = "userId", description = "用户id") @RequestParam(required = true) Long userId,
            @ApiQueryParam(name = "unfollowUserId", description = "被取消的用户id") @RequestParam(required = true) Long unfollowUserId
    ) {
        try {
            log.info("取消关注入参：用户id：{}，被取消关注的用户id：{}", userId, unfollowUserId);
            return Response.ok(userService.unfollowUserId(userId, unfollowUserId));
        } catch (Exception e) {
            log.info("取消关注失败：{}", e);
            return Response.exception(e);
        }
    }

    @ApiMethod(description = "根据用户ID分页获取我的关注列表")
    @GetMapping("/getMyFollowList")
    public Response<ResponsePage.Page<ZpFollowResponse>> getWorkDetailsComment(
            @ApiQueryParam(name = "userId", description = "用户id") @RequestParam(required = true) Long userId,
            @ApiQueryParam(name = "pageNumber", description = "页码") @RequestParam(required = true) Integer pageNumber,
            @ApiQueryParam(name = "pageSize", description = "条数") @RequestParam(required = true) Integer pageSize
    ) {
        try {
            log.info("根据用户ID获取我的关注列表入参：用户ID：{}，页码：{}，条数：{}", userId, pageNumber, pageSize);
            return Response.ok(userService.getMyFollowList(userId, pageNumber, pageSize));
        } catch (Exception e) {
            log.info("获取关注列表失败：{}", e);
            return Response.exception(e);
        }
    }

    @ApiMethod(description = "根据用户ID分页获取我的粉丝列表")
    @GetMapping("/getMyFansList")
    public Response<ResponsePage.Page<ZpFollowResponse>> getMyFansList(
            @ApiQueryParam(name = "userId", description = "用户id") @RequestParam(required = true) Long userId,
            @ApiQueryParam(name = "pageNumber", description = "页码") @RequestParam(required = true) Integer pageNumber,
            @ApiQueryParam(name = "pageSize", description = "条数") @RequestParam(required = true) Integer pageSize
    ) {
        try {
            log.info("根据用户ID获取粉丝入参：用户ID：{}，页码：{}，条数：{}", userId, pageNumber, pageSize);
            return Response.ok(userService.getMyFansList(userId, pageNumber, pageSize));
        } catch (Exception e) {
            log.info("获取粉丝列表失败：{}", e);
            return Response.exception(e);
        }
    }

    @PostMapping("/hd")
    public R getHd(@RequestParam(required = true) Long userId) {
        try {
          List<ZphdResponse> l =  userService.hd(userId);
            return R.ok().put("data",l);
        } catch (Exception e) {
            log.info("关注失败：{}", e);
            return R.error();
        }
    }
}
