package org.zhire.work.entity.works.zuopin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.zhire.work.entity.works.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@ApiModel(description = "作品评论")
@Table(name = "zp_works_comment",indexes = {
        @Index(name = "c_works_id",columnList = "works_id"),
        @Index(name = "c_works_comment_user_id",columnList = "works_comment_user_id"),
        @Index(name = "c_works_comment_user_type",columnList = "works_comment_user_type")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpWorksComment extends BaseEntity {

    @ApiModelProperty("作品Id")
    @Column(name = "works_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '作品Id'")
    private Long worksId;

    @ApiModelProperty("用户名称")
    @Column(name = "username", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '用户名称'")
    private String username;

    @ApiModelProperty("头像")
    @Column(name = "head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '头像'")
    private String head = null;

    @ApiModelProperty("评论人类型 0：老师点评 1：学生评论")
    @Column(name = "works_comment_user_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '评论人类型'")
    private COMMENT_USER_TYPE worksCommentUserType;

    @ApiModelProperty("评论人ID")
    @Column(name = "works_comment_user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '评论人ID'")
    private Long worksCommentUserId;

    @ApiModelProperty("语音评论内容地址")
    @Column(name = "works_video_content", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_1024 + " COMMENT '语音评论内容地址'")
    private String worksVideoContent;

    @ApiModelProperty("语音点评秒")
    @Column(name = "sound_comment_second", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '语音点评秒'")
    private String soundCommentSecond;

    @ApiModelProperty("文字评论内容")
    @Column(name = "works_words_content", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '文字评论内容'")
    private String worksWordsContent;

    @ApiModelProperty("状态")
    @Column(name = "status", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '状态'")
    private STATUS status;

    @ApiModel(description = "评论人类型 1：老师点评 2：学生评论")
    public enum COMMENT_USER_TYPE {
        /**
         * 默认
         */
        DEFAULT,
        /**
         * 老师
         */
        TEACHER,

        /**
         * 学生
         */
        STUDENT
    }

}
