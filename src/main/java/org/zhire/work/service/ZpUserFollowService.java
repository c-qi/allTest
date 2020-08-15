package org.zhire.work.service;


import org.zhire.work.entity.works.user.ZpUserFollow;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.ZpFollowResponse;

import java.util.List;

public interface ZpUserFollowService {

    List<ZpUserFollow> findAllByUserId(Long userId);

    List<Long> getAllMyFollowUserIds(Long userId);

    ZpUserFollow  follow(Long userId, String username, Long followUserId, String followUsername);

    ZpUserFollow unfollowUserId(Long userId, Long unfollowUserId);

    ResponsePage.Page<ZpFollowResponse> getMyFollowList(Long userId, Integer pageNumber, Integer pageSize);

    ResponsePage.Page<ZpFollowResponse> getMyFansList(Long userId, Integer pageNumber, Integer pageSize);

    List<ZpFollowResponse> getMyFollowByUsername(Long userId, Long followUserId, String followUsername);

    List<ZpFollowResponse> getMyFansByUsernameOrId(Long userId, Long fansUserId, String fansUsername);

    void updateFollowUsernameByUserId(Long userId, String username);
}
