package org.zhire.work.es.zuopin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.zuopin.ZpWorks;
import org.zhire.work.es.BaseRepository;
import org.zhire.work.es.v1.ZpWorksRepositoryV1;

public interface ZpWorksRepository extends
        JpaRepository<ZpWorks, Long>,
        BaseRepository<ZpWorks, Long>,
        ZpWorksRepositoryV1 {
}
