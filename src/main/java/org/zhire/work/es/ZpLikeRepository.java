package org.zhire.work.es;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zhire.work.entity.works.zuopin.ZpWorksLike;
import org.zhire.work.es.v1.ZpWorksLikeRepositoryV1;
import org.zhire.work.es.zuopin.ZpWorksLikeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ZpLikeRepository extends ZpWorksLikeRepository, ZpWorksLikeRepositoryV1 {



    @Query(value = "SELECT " +
            "l.id, " +
            "l.works_id AS worksId, " +
            "l.works_like_user_id AS worksLikeUserId, " +
            "u.username, " +
            "u.head AS userHead " +
            "FROM " +
            "w_works_like l,w_works_user u  " +
            "WHERE " +
            "l.works_like_user_id = u.id  " +
            "AND l.works_id IN (?1) AND l.del=0 order by l.create_time desc ",nativeQuery = true)
    List<Map<String,Object>> findAllWorksLikeByWorksIds(List<Long> worksIds);

    @Query(value = "select " +
            "l.id id, " +
            "l.works_id  worksId, " +
            "l.works_like_user_id  worksLikeUserId, " +
            "u.username username, " +
            "u.head  head " +
            "from " +
            "zp_works_likes l " +
            "left join zp_user u  " +
            "on l.works_like_user_id = u.id " +
            "where l.works_id = ?1 order by l.ctime desc",nativeQuery = true)
    List<Map<String,Object>> findAllWorksLikeByWorksId(Long worksId);

    @Query(value = "select count(id) from w_works_like where works_like_user_id = ?1 AND del=0 ",nativeQuery = true)
    Integer findMineLikeWorksCount(Long worksLikeUserId);

    @Query(value = "select wu.id worksUserId,wl.works_id worksId, wu.username username,wu.head,wu.from_user_id fromUserId,wu.bear_user_id bearUserId " +
            "from w_works_like wl " +
            "left join w_works_user wu on wu.id =wl.works_like_user_id    " +
            "where wl.works_id in(?1) AND wl.del=0  AND wu.id is not null order by wl.create_time desc ",nativeQuery = true)
    List<Map<String,String>>  findListByWorksIds(List<Long> idList);

    Optional<ZpWorksLike> findFirstByWorksIdAndWorksLikeUserId(Long worksId, Long worksLikeUserId);


    List<ZpWorksLike> findFirst10ByWorksId(Long worksId, Sort sort);

    Long countByWorksId(Long worksId);
}
