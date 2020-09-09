package org.zhire.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameOrEmail(String userName, String email);

    User findByUserName(String userName);

    @Query(value = "select * from user limit ?1, ?2", nativeQuery = true)
    List<User> findAllUser(int page, int pazeSize);

    @Query(value = "select `user`.id id , `user`.user_name userName ," +
            "user_info.id userInfoId ,user_info.user_address userAddress " +
            "from `user` left  join `user_info` on `user`.id = `user_info`.user_id limit " +
            "?1, ?2", nativeQuery = true)
    List<Map<String, Object>> findAllUserInfo(int page, int pazeSize);

    Page<User> findAllByFlag(Pageable pageable, int i);
}

