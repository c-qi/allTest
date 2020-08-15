package org.zhire.work.entity.works.zuopin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.zhire.work.entity.works.BaseEntity;
import org.zhire.work.entity.works.weixin.ZpWeiXin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@ApiModel(description = "喜欢的作品")
@Table(name = "zp_works_likes",indexes = {
        @Index(name = "l_works_like_user_id", columnList = "works_like_user_id"),
        @Index(name = "l_works_id", columnList = "works_id")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpWorksLike extends BaseEntity {

    @ApiModelProperty("点赞人ID")
    @Column(name = "works_like_user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '点赞人ID'")
    private Long worksLikeUserId;

    @ApiModelProperty("作品Id")
    @Column(name = "works_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '作品Id'")
    private Long worksId;

    @ApiModelProperty("用户名称")
    @Column(name = "username", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '用户名称'")
    private String username;

    @ApiModelProperty("头像")
    @Column(name = "head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '头像'")
    private String head = null;

    @ApiModelProperty("用户类型（0 默认， 1 app用户，2 微信用户）")
    @Column(name = "user_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '用户类型（0 默认， 1 app用户，2 微信用户）'")
    private LIKE_USER_TYPE userType;

    @ApiModelProperty("openId")
    @Column(name = "openid", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT 'openId'")
    private String openid;

    @ApiModelProperty("公众号标识")
    @Column(name = "gzh_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '公众号标识'")
    private ZpWeiXin.GZHTYPE gzhType;

    @ApiModelProperty("unionid")
    @Column(name = "unionid", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT 'unionid'")
    private String unionid;

    @ApiModelProperty(" 状态")
    @Column(name = "status", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT ' 状态'")
    private STATUS status;

    @ApiModel(description = "点赞用户类型 0 默认， 1 app用户，2 微信用户")
    public enum LIKE_USER_TYPE {
        /**
         * 默认
         */
        DEFAULT,
        /**
         * app用户
         */
        APP_USER,

        /**
         * 微信用户
         */
        WEIXIN_USER
    }

}
