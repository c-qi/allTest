package org.zhire.work.es.zuopin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.zuopin.ZpWorksLike;
import org.zhire.work.es.BaseRepository;
import org.zhire.work.es.v1.ZpWorksLikeRepositoryV1;

public interface ZpWorksLikeRepository extends
        JpaRepository<ZpWorksLike, Long>,
        BaseRepository<ZpWorksLike, Long>,
        ZpWorksLikeRepositoryV1 {
}
