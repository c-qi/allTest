package org.zhire.work.es.zuopin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.zuopin.ZpWorksComment;
import org.zhire.work.es.BaseRepository;
import org.zhire.work.es.v1.ZpWorksCommentRepositoryV1;

public interface ZpWorksCommentRepository extends
        JpaRepository<ZpWorksComment, Long>,
        BaseRepository<ZpWorksComment, Long>,
        ZpWorksCommentRepositoryV1 {
}
