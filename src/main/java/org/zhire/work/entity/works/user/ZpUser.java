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

@ApiModel(description = "作品集用户")
@Table(name = "zp_user", indexes = {
        @Index(name = "u_mobile", columnList = "mobile"),
        @Index(name = "u_province_city_area", columnList = "province,city,area"),

})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpUser extends BaseEntity {

    @ApiModelProperty("用户名称")
    @Column(name = "username", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '用户名称'")
    private String username;

    @ApiModelProperty("头像")
    @Column(name = "head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '头像'")
    private String head = null;

    @ApiModelProperty("用户微信昵称")
    @Column(name = "weixin_nickname", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '用户微信昵称'")
    private String weixinNickname;

    @ApiModelProperty("手机号")
    @Column(name = "mobile", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '手机号'")
    private String mobile;

    @ApiModelProperty("用户性别")
    @Column(name = "sex", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '用户性别'")
    private SEX sex;

    @ApiModelProperty("用户生日")
    @Column(name = "birthday", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '用户生日'")
    private Long birthday;

    @ApiModelProperty("省")
    @Column(name = "province", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '省'")
    private String province = null;

    @ApiModelProperty("市")
    @Column(name = "city", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '市'")
    private String city = null;

    @ApiModelProperty("区")
    @Column(name = "area", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '区'")
    private String area = null;

    @ApiModelProperty("评论人数")
    @Column(name = "comment_count", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '评论人数'")
    private Long commentCount;

    @ApiModelProperty("点赞人数")
    @Column(name = "like_count", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '点赞人数'")
    private Long likeCount;

    @ApiModelProperty("被关注人数")
    @Column(name = "follow_count", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '被关注人数'")
    private Long followCount;

    @ApiModel(description = "用户性别")
    public enum SEX {
        /**
         * 默认
         */
        DEFAULT,

        /**
         * 男性
         */
        BOY,

        /**
         * 女性
         */
        GIRL,

        /**
         * 保密
         */
        SECRECY
    }

}
