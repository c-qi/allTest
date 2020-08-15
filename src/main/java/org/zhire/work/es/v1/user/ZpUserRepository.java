package org.zhire.work.es.v1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.es.BaseRepository;

public interface ZpUserRepository extends
        JpaRepository<ZpUser, Long>,
        BaseRepository<ZpUser, Long>,
        ZpUserRepositoryV1 {
}
