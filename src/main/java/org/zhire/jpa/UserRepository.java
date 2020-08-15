package org.zhire.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
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

    @Modifying
    @Transactional
    @Query(value = "update user_copy1 u " +
            "set  u.user_name = :userName, " +
            "u.pass_word = :password " +
            "where u.works_id = :id ",nativeQuery = true)
    int updateByid(Integer id, String userName, String password);
}

