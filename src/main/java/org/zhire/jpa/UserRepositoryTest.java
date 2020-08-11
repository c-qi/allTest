package org.zhire.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepositoryTest extends JpaRepository<ZpUserBusiness, Long> {

    Optional<ZpUserBusiness> findFirstByFromType(ZpUserBusiness.FROMTYPE fromType);
}

