package org.zhire.work.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhire.work.entity.works.user.ZpTeacher;
import org.zhire.work.entity.works.user.ZpUser;
import org.zhire.work.entity.works.zuopin.ZpWorks;
import org.zhire.work.entity.works.zuopin.ZpWorksComment;
import org.zhire.work.es.ZpCommentRepository;
import org.zhire.work.es.v1.user.ZpTeacherRepository;
import org.zhire.work.es.v1.user.ZpUserRepository;
import org.zhire.work.es.zuopin.ZpWorksRepository;
import org.zhire.work.model.ResponsePage;
import org.zhire.work.model.vo.ZpWorksCommentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ZpWorksCommentServiceImpl implements ZpWorksCommentService {


    @Autowired
    private ZpCommentRepository zpWorksCommentRepository;

    @Autowired
    private ZpUserRepository zpUserRepository;

    @Autowired
    private ZpTeacherRepository zpTeacherRepository;

    @Autowired
    private ZpWorksRepository zpWorksRepository;

    /**
     * 老师点评和用户的评论
     *
     * @param worksComment 作品
     * @return
     */
    @Override
    @Transactional
    public ZpWorksComment saveWorksComment(ZpWorksComment worksComment, Long worksUserId) {
        // 如果是老师的点评
        if (worksComment.getWorksCommentUserType().equals(ZpWorksComment.COMMENT_USER_TYPE.TEACHER)) {
            // 如果老师对该作品评论过则更新评论
            Optional<ZpWorksComment> comment = zpWorksCommentRepository.findFirstByWorksIdAndWorksCommentUserIdAndWorksCommentUserType(worksComment.getWorksId(), worksComment.getWorksCommentUserId(),ZpWorksComment.COMMENT_USER_TYPE.TEACHER);
            if (comment.isPresent()) {
                ZpWorksComment zpWorksComment = comment.get();
                log.info("老师点评过作品，开始更新评论：{}", zpWorksComment);
                zpWorksComment.setSoundCommentSecond(worksComment.getSoundCommentSecond());
                zpWorksComment.setWorksCommentUserType(worksComment.getWorksCommentUserType());
                zpWorksComment.setWorksCommentUserId(worksComment.getWorksCommentUserId());
                zpWorksComment.setWorksVideoContent(worksComment.getWorksVideoContent());
                zpWorksComment.setSoundCommentSecond(worksComment.getSoundCommentSecond());
                zpWorksComment.setWorksWordsContent(worksComment.getWorksWordsContent());
                return zpWorksCommentRepository.save(zpWorksComment);
            } else {
                // 保存老师点评
                ZpWorksComment save = zpWorksCommentRepository.save(worksComment);
                // 更新user表评论总数
                saveCount(worksUserId);
                // 更新作品表老师点评总数
                saveWorksTeacherCount(worksComment.getWorksId());
                log.info("老师第一次保点评结果：{}", save);
                return save;
            }
        } else {
            // 保存用户的评论
            ZpWorksComment save = zpWorksCommentRepository.save(worksComment);
            // 更新user表评论总数
            saveCount(worksUserId);
            // 更新作品表评论总数
            saveWorksCount(worksComment.getWorksId());
            log.info("保存用户评论结果：{}", save);
            return save;
        }
    }

    /**
     * 更新用户表评论数
     *
     * @param userId 评论用户的ID
     */
    private void saveCount(Long userId) {
        Optional<ZpUser> user = zpUserRepository.findById(userId);
        if (user.isPresent()) {
            ZpUser zpUser = user.get();
            Long count = zpUser.getCommentCount();
            zpUser.setCommentCount(count + 1);
            ZpUser save = zpUserRepository.save(zpUser);
            log.info("更新用户评论数结果：{}", save);
        }
    }

    /**
     * 更新作品表用户评论总数
     *
     * @param worksId
     */
    private void saveWorksCount(Long worksId) {
        Optional<ZpWorks> zpWorks = zpWorksRepository.findById(worksId);
        if (zpWorks.isPresent()) {
            ZpWorks works = zpWorks.get();
            Integer count = works.getWorksCommentCount();
            // 如果是第一次评论，设置首次评论时间
            if (0 == count) {
                works.setCommentFirstTime(System.currentTimeMillis());
            }
            works.setWorksCommentCount(count + 1);
            ZpWorks save = zpWorksRepository.save(works);
            log.info("更新作品表评论数结果：{}", save);
        }
    }

    /**
     * 更新作品表老师点评总数
     *
     * @param worksId
     */
    private void saveWorksTeacherCount(Long worksId) {
        Optional<ZpWorks> zpWorks = zpWorksRepository.findById(worksId);
        if (zpWorks.isPresent()) {
            ZpWorks works = zpWorks.get();
            Integer count = works.getReviewsCount();
            // 如果是第一次评论，设置首次评论时间
            if (0 == count) {
                works.setReviewsFirstTime(System.currentTimeMillis());
            }
            works.setReviewsCount(count + 1);
            ZpWorks save = zpWorksRepository.save(works);
            log.info("更新作品表评论数结果：{}", save);
        }
    }

    /**
     * 根据作品ID分页查询评论信息
     *
     * @param worksId    作品ID
     * @param pageNumber 当前页
     * @param pageSize   每页显示条数
     * @return
     */
    @Override
    public ResponsePage.Page<ZpWorksCommentResponse> queryWorksAllComment(Long worksId, Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "ctime");
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort);
        Page<ZpWorksComment> zpWorksComments = zpWorksCommentRepository.findAllByWorksId(worksId, pageable);
        zpWorksComments.get().forEach(System.out::println);
        List<ZpWorksCommentResponse> list = new ArrayList<>();
        for (ZpWorksComment zpWorksComment : zpWorksComments) {
            ZpWorksCommentResponse zpWorksResponse = new ZpWorksCommentResponse();
            if (zpWorksComment.getWorksCommentUserType().equals(ZpWorksComment.COMMENT_USER_TYPE.TEACHER)) {
                Optional<ZpTeacher> zpTeacher = zpTeacherRepository.findById(zpWorksComment.getWorksCommentUserId());
                if (zpTeacher.isPresent()) {
                    zpWorksResponse.setTeacherHead(zpTeacher.get().getTeacherHead());
                    zpWorksResponse.setTeacherNickname(zpTeacher.get().getTeacherNickname());
                    list.add(zpWorksResponse);
                }
            } else {
                Optional<ZpUser> user = zpUserRepository.findById(zpWorksComment.getWorksCommentUserId());
                if (user.isPresent()) {
                    zpWorksResponse.setHead(user.get().getHead());
                    zpWorksResponse.setUsername(user.get().getUsername());
                    list.add(zpWorksResponse);
                }
            }
        }

        ResponsePage.Page<ZpWorksCommentResponse> result = new ResponsePage.Page<>();
//        int pageNum = (pageNumber - 1) * pageSize;
//        List<Map<String, Object>> maps = zpWorksCommentRepository.findWorksCommentByPage(worksId, pageNum, pageSize);
        long count = zpWorksCommentRepository.countByWorksId(worksId);
        long totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
//        List<ZpWorksCommentResponse> worksCommentResponseList = JSON.parseArray(JSON.toJSONString(maps), ZpWorksCommentResponse.class);
        result.setContent(list)
                .setSize(pageSize)
                .setTotalElements(count)
                .setTotalPages(totalPage)
                .setNumber(pageNumber)
                .setEmpty(true);
        log.info("分页获取作品评论列表出参：{}", JSON.toJSONString(result));
        return result;
    }


}
