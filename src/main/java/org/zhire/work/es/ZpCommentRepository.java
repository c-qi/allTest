package org.zhire.work.es;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zhire.work.entity.works.zuopin.ZpWorksComment;
import org.zhire.work.es.v1.ZpWorksCommentRepositoryV1;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ZpCommentRepository extends org.zhire.work.es.zuopin.ZpWorksCommentRepository , ZpWorksCommentRepositoryV1 {


    @Query(value = "SELECT " +
            "c.id, " +
            "c.works_id AS worksId, " +
            "c.works_comment_user_type AS worksCommentUserType, " +
            "c.works_comment_user_id AS worksCommentUserId, " +
            "c.works_video_content AS worksVideoContent, " +
            "c.sound_comment_second AS soundCommentSecond, " +
            "c.works_words_content AS worksWordsContent, " +
            "IF ( LENGTH( u.username )= 0, '美术宝用户', u.username )  AS username, " +
            "u.head  AS userHead  " +
            "FROM " +
            "w_works_comment c " +
            "LEFT JOIN w_works_user u ON c.works_comment_user_id = u.id " +
            "WHERE " +
            "c.works_id IN (?1) AND c.works_comment_user_type=1 " +
            "ORDER BY " +
            "c.works_comment_user_type ASC, " +
            "c.create_time DESC limit ?2,?3", nativeQuery = true)
    List<Map<String, Object>> findStudentCommentPageByWorksId(Long worksId, Integer startNum, Integer size);

    @Query(value = "SELECT " +
            "c.id, " +
            "c.works_id AS worksId, " +
            "c.works_comment_user_type AS worksCommentUserType, " +
            "c.works_comment_user_id AS worksCommentUserId, " +
            "c.works_video_content AS worksVideoContent, " +
            "c.sound_comment_second AS soundCommentSecond, " +
            "c.works_words_content AS worksWordsContent, " +
            "IF " +
            "( LENGTH( t.teacher_name )= 0, t.id, t.teacher_name ) AS username, " +
            "t.head_image AS userHead  " +
            "FROM " +
            "w_works_comment c " +
            "LEFT JOIN w_works_teacher t ON c.works_comment_user_id = t.id  " +
            "WHERE " +
            "c.works_id IN (?1)  AND c.works_comment_user_type=0 " +
            "ORDER BY " +
            "c.works_comment_user_type ASC, " +
            "c.create_time DESC ", nativeQuery = true)
    List<Map<String, Object>> findTeacherCommentPageByWorksId(Long worksId);


    @Query(value = "SELECT " +
            "c.id, " +
            "c.works_id AS worksId, " +
            "c.works_comment_user_type AS worksCommentUserType, " +
            "c.works_comment_user_id AS worksCommentUserId, " +
            "c.works_video_content AS worksVideoContent, " +
            "c.sound_comment_second AS soundCommentSecond, " +
            "c.works_words_content AS worksWordsContent, " +
            "CASE " +
            "c.works_comment_user_type  " +
            "WHEN 0 THEN " +
            "IF " +
            "( LENGTH( t.teacher_name )= 0, t.id, t.teacher_name ) ELSE " +
            "IF " +
            "( LENGTH( u.username )= 0, '美术宝用户', u.username )  " +
            "END AS username, " +
            "CASE " +
            "c.works_comment_user_type  " +
            "WHEN 0 THEN " +
            "t.head_image ELSE u.head  " +
            "END AS userHead  " +
            "FROM " +
            "w_works_comment c " +
            "LEFT JOIN w_works_user u ON c.works_comment_user_id = u.id " +
            "LEFT JOIN w_works_teacher t ON c.works_comment_user_id = t.id  " +
            "WHERE " +
            "c.works_id  = ?1   " +
            "AND IF(c.works_comment_user_type=1,u.id is not null ,t.id is not null)  " +
            "ORDER BY " +
            "c.works_comment_user_type ASC, " +
            "c.create_time DESC ", nativeQuery = true)
    List<Map<String, Object>> findAllByWorksId(Long worksId);

    @Query(value = "SELECT " +
            "c.id, " +
            "c.works_id AS worksId, " +
            "c.works_comment_user_type AS worksCommentUserType, " +
            "c.works_comment_user_id AS worksCommentUserId, " +
            "c.works_video_content AS worksVideoContent, " +
            "c.sound_comment_second AS soundCommentSecond, " +
            "c.works_words_content AS worksWordsContent, " +
            "CASE " +
            "c.works_comment_user_type  " +
            "WHEN 0 THEN " +
            "IF " +
            "( LENGTH( t.teacher_name )= 0, t.id, t.teacher_name ) ELSE " +
            "IF " +
            "( LENGTH( u.username )= 0, '美术宝用户', u.username )  " +
            "END AS username, " +
            "CASE " +
            "c.works_comment_user_type  " +
            "WHEN 0 THEN " +
            "t.head_image ELSE u.head  " +
            "END AS userHead  " +
            "FROM " +
            "w_works_comment c " +
            "LEFT JOIN w_works_user u ON c.works_comment_user_id = u.id " +
            "LEFT JOIN w_works_teacher t ON c.works_comment_user_id = t.id  " +
            "WHERE " +
            "c.works_id IN (?1)  " +
            "AND IF(c.works_comment_user_type=1,u.id is not null ,t.id is not null)  " +
            "ORDER BY " +
            "c.works_comment_user_type ASC, " +
            "c.create_time DESC ", nativeQuery = true)
    List<Map<String, Object>> findListByWorksId(List<Long> idList);

    @Query(value = "SELECT  " +
            "c.id, " +
            "c.works_id AS worksId, " +
            "c.works_comment_user_type AS worksCommentUserType, " +
            "c.works_comment_user_id AS worksCommentUserId, " +
            "c.works_video_content AS worksVideoContent, " +
            "c.sound_comment_second AS soundCommentSecond, " +
            "c.works_words_content AS worksWordsContent, " +
            "if(LENGTH(t.teacher_name)=0,t.id,t.teacher_name) AS username, " +
            "t.head_image AS userHead " +
            "FROM w_works_comment c,w_works_teacher t WHERE c.works_comment_user_type=0 AND c.works_comment_user_id=t.id AND c.works_id in (?1)", nativeQuery = true)
    List<Map<String, Object>> findAllTeacherCommentByWorksIds(List<Long> worksIds);




    @Query(value = "select c.works_id worksId, " +
            "c.works_comment_user_type worksCommentUserType ," +
            "c.works_comment_user_id worksCommentUserId," +
            "c.works_video_content worksVideoContent, " +
            "c.sound_comment_second  soundCommentSecond, " +
            "c.works_words_content worksWordsContent," +
            "u.username username," +
            "u.head head," +
            "t.teacher_nickname teacherNickname, " +
            "t.teacher_head teacherHead " +
            "from zp_works_comment c " +
            "left join zp_user u on c.works_comment_user_id = u.id " +
            "left join zp_teacher t on c.works_comment_user_id = t.id " +
            "where c.works_id = ?1 " +
            "order by c.ctime desc " +
            "limit ?2, ?3",
            nativeQuery = true)
    List<Map<String, Object>> findWorksCommentByPage(Long worksId, Integer pageNum, Integer pageSize);

    Page<ZpWorksComment> findAllByWorksId(Long worksId, Pageable pageable);

    long countByWorksId(Long worksId);

    Optional<ZpWorksComment> findFirstByWorksIdAndWorksCommentUserId(Long worksId, Long worksCommentUserId);

    Optional<ZpWorksComment> findFirstByWorksIdAndWorksCommentUserIdAndWorksCommentUserType(Long worksId, Long worksCommentUserId, ZpWorksComment.COMMENT_USER_TYPE teacher);
}
