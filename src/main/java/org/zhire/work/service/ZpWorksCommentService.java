package org.zhire.work.service;

import org.zhire.work.entity.works.zuopin.ZpWorksComment;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.vo.ZpWorksCommentResponse;

public interface ZpWorksCommentService {

    ZpWorksComment saveWorksComment(ZpWorksComment worksComment, Long worksUserId);


    ResponsePage.Page<ZpWorksCommentResponse> queryWorksAllComment(Long worksId, Integer pageNumber, Integer pageSize);
}
