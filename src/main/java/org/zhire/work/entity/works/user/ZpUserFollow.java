package org.zhire.work.entity.works.user;

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

@ApiModel(description = "用户关注表")
@Table(name = "zp_user_follow", indexes = {
        @Index(name = "zp_user_id", columnList = "user_id"),
        @Index(name = "zp_follow_user_id", columnList = "follow_user_id")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpUserFollow extends BaseEntity {

    @ApiModelProperty("关注人ID")
    @Column(name = "user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '关注人ID'")
    private Long userId;

    @ApiModelProperty("关注人的名称")
    @Column(name = "username", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '关注人的名称'")
    private String username;

    @ApiModelProperty("被关注人ID")
    @Column(name = "follow_user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '被关注人ID'")
    private Long followUserId;

    @ApiModelProperty("被关注人的名称")
    @Column(name = "follow_username", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '被关注人的名称'")
    private String followUsername;

    @ApiModelProperty("最新关注时间")
    @Column(name = "latest_time", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '最新关注时间'")
    private Long latestTime;

    @ApiModelProperty("用户关注状态0:未关注 1:已关注 2: 回关 3:互相关注")
    @Column(name = "follow_type", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '用户关注状态0:未关注 1:已关注 2: 回关 3:互相关注'")
    private FOLLOWTYPE followType;

    @ApiModel(description = "用户关注状态0:未关注 1:已关注 2: 回关 3:互相关注")
    public enum FOLLOWTYPE {
        /**
         * 默认 0 未关注
         */
        DEFAULT,

        /**
         * 1 已关注
         */
        FOLLOWED,

        /**
         * 2 回关
         */
        BACK_FOLLOWED,

        /**
         * 3 互相关注
         */
        BOTH_FOLLOWED
    }


}
