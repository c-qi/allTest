package org.zhire.work.es.v1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.user.ZpUserBusiness;
import org.zhire.work.es.BaseRepository;

public interface ZpUserBusinessRepository extends
        JpaRepository<ZpUserBusiness, Long>,
        BaseRepository<ZpUserBusiness, Long>,
        ZpUserBusinessRepositoryV1 {
}
