package org.zhire.work.es.v1.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zhire.work.entity.works.user.ZpTeacher;
import org.zhire.work.es.BaseRepository;

public interface ZpTeacherBusinessRepository extends
        JpaRepository<ZpTeacher, Long>,
        BaseRepository<ZpTeacher, Long>,
        ZpTeacherRepositoryV1 {
}
