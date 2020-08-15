package org.zhire.work.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ZpWorksCommentResponse {
    // select c.works_id worksId,
    // c.works_comment_user_type worksCommentUserType ,
    // c.works_comment_user_id worksCommentUserId,
    // c.works_video_content worksVideoContent,
    // c.sound_comment_second  soundCommentSecond,
    // c.works_words_content worksWordsContent,
    // u.username username,
    // u.head head,
    // t.teacher_nickname teacherNickname,
    // t.teacher_head teacherHead
    // from zp_works_comment c
    // left join zp_user u on c.works_comment_user_id = u.id
    // left join zp_teacher t on c.works_comment_user_id = t.id


    @ApiModelProperty("用户头像")
    private String head;

    @ApiModelProperty("老师头像")
    private String teacherHead;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("老师名称")
    private String teacherNickname;


}
