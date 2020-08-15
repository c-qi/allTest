package org.zhire.work.es.v1.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zhire.work.entity.works.user.ZpUserFollow;
import org.zhire.work.es.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface ZpUserFollowRepository extends
        JpaRepository<ZpUserFollow, Long>,
        BaseRepository<ZpUserFollow, Long>,
        ZpUserFollowRepositoryV1 {
    Optional<ZpUserFollow> findByUserIdAndFollowUserId(Long followUserId, Long userId);

//    @Query(value = "SELECT *  " +
//            "FROM zp_user_follow  " +
//            "WHERE user_id = ?1 " +
//            "AND follow_type = ?2 " ,
//            nativeQuery = true)
    List<ZpUserFollow> findByUserIdAndFollowType(Long userId, ZpUserFollow.FOLLOWTYPE followed);

    long countByUserIdAndFollowType(Long userId, ZpUserFollow.FOLLOWTYPE followed);

    @Query(value = "SELECT *  " +
            "FROM zp_user_follow  " +
            "WHERE user_id = ?1 " +
            "AND (follow_type = ?2 or " +
            "follow_type = ?3) ",
            nativeQuery = true)
    List<ZpUserFollow> findByUserIdAndFollowTypeOrFollowType(Long userId, ZpUserFollow.FOLLOWTYPE followed, ZpUserFollow.FOLLOWTYPE bothFollowed);

    Page<ZpUserFollow> findByFollowUserIdAndFollowTypeOrFollowType(Long userId, ZpUserFollow.FOLLOWTYPE followed, ZpUserFollow.FOLLOWTYPE bothFollowed, Pageable pageable);

//    @Query(value = "SELECT *  " +
//            "FROM zp_user_follow  " +
//            "WHERE user_id = ?1 " +
//            "AND follow_type in ?2 " ,
//            nativeQuery = true)
    Page<ZpUserFollow> findByUserIdAndFollowTypeIn(Long userId, List<ZpUserFollow.FOLLOWTYPE> typeList, Pageable pageable);

    Page<ZpUserFollow> findByFollowUserIdAndFollowTypeIn(Long userId, List<ZpUserFollow.FOLLOWTYPE> followType, Pageable pageable);


        @Query(value = "SELECT *  " +
            "FROM zp_user_follow  " +
            "WHERE follow_type in :typeList " +
            "AND  user_id = :userId  " ,
            nativeQuery = true)
    List<ZpUserFollow> findByFollowTypeInAndUserId(List<ZpUserFollow.FOLLOWTYPE> typeList,Long userId );

//    @Query(value = "SELECT *  " +
//            "FROM zp_user_follow  " +
//            "WHERE user_id = ?1 " +
//            "AND follow_type in ?2 ",
//            nativeQuery = true)
    List<ZpUserFollow> findAllByUserIdAndFollowTypeIn(Long userId,List<ZpUserFollow.FOLLOWTYPE> typeList);

    @Query(value = "SELECT  " +
            "id,  " +
            "works_id worksId, " +
            "username, " +
            "head, " +
            "ctime, " +
            "'null' as worksCommentUserId, " +
            "'1' as worksCommentUserType, " +
            "'null' as worksVideoContent, " +
            "'null' as worksWordsContent " +
            "from zp_works_likes " +
            "where works_user_id = 1 " +
            "and del = 0 " +
            "union all  " +
            "select  " +
            "id, " +
            "works_id worksId," +
            "username, " +
            "head, " +
            "ctime, " +
            "works_comment_user_id worksCommentUserId, " +
            "works_comment_user_type worksCommentUserType, " +
            "works_video_content worksVideoContent, " +
            "works_words_content worksWordsContent " +
            "FROM zp_works_comment " +
            "WHERE works_user_id = 1 " +
            "ORDER BY ctime desc " , nativeQuery = true)
    List<Map<Object,Object>> getHd(Long userId);
}
