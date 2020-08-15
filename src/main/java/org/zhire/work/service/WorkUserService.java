package org.zhire.work.service;


import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.entity.works.user.ZpUserFollow;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.ZpFollowResponse;
import org.zhire.work.model.ZphdResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WorkUserService {

    Optional<Map<Long, ZpUser>>findAllByIdsOfMap(List<Long> ids);

    Optional<List<ZpUser>> findAllByIds(List<Long> ids);

    ZpUserFollow follow(Long userId, Long followUserId);

    ZpUserFollow unfollowUserId(Long userId, Long unfollowUserId);

    ResponsePage.Page<ZpFollowResponse> getMyFollowList(Long userId, Integer pageNumber, Integer pageSize);

    ResponsePage.Page<ZpFollowResponse> getMyFansList(Long userId, Integer pageNumber, Integer pageSize);

    List<ZphdResponse> hd(Long userId);
}
