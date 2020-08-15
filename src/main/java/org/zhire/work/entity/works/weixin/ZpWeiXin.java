package org.zhire.work.entity.works.weixin;

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
import javax.persistence.Table;

/**
 * @author qinzhenlei
 */
@ApiModel(description = "作品广场用户微信信息")
@Table(name = "zp_weixin")

@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpWeiXin extends BaseEntity {

    @ApiModelProperty("公众号分类")
    @Column(name = "gzh_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '公众号分类'")
    private GZHTYPE gzhType;

    @ApiModelProperty("对应公众号主体用户的openId")
    @Column(name = "openid", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '对应公众号主体用户的openId'")
    private String openid;

    @ApiModelProperty("授权手机号")
    @Column(name = "authorize_mobile", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '授权手机号'")
    private String authorizeMobile = null;

    @ApiModelProperty("用户ID")
    @Column(name = "user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '用户ID'")
    private Long userId = null;

    @ApiModelProperty("微信名称")
    @Column(name = "nickname", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '微信名称'")
    private String nickname = null;

    @ApiModelProperty("微信头像")
    @Column(name = "avatar", columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '微信头像'")
    private String avatar = "NULL";

    @ApiModelProperty("省")
    @Column(name = "province", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '省'")
    private String province = null;

    @ApiModelProperty("市")
    @Column(name = "city", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '市'")
    private String city = null;

    @ApiModelProperty("区")
    @Column(name = "area", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '区'")
    private String area = null;

    @ApiModelProperty("用户性别")
    @Column(name = "sex", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '用户性别'")
    private Integer sex;

    /**---- 首次关注公众号时间 ----**/
    @ApiModelProperty("首次关注公众号时间")
    @Column(name = "fast_follow_time", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '首次关注公众号时间'")
    private Long fastFollowTime = null;

    @ApiModelProperty("用户是否关注公众号")
    @Column(name = "is_follow", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '用户是否关注公众号'")
    private FOLLOW isFollow = null;

    @ApiModelProperty("微信用户开放平台唯一ID")
    @Column(name = "unionid", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '微信用户开放平台唯一ID'")
    private String unionid = null;


    @ApiModel(description = "用户是否关注公众号")
    public enum FOLLOW {
        /**
         * 未关注公众号
         */
        DEFAULT,

        /**
         * 关注公众号
         */
        FOLLOW,
    }

    @ApiModel(description = "公众号分类")
    public enum GZHTYPE {
        /**
         * 默认
         */
        DEFAULT,

        /**
         * 小熊美术ai课
         */
        AI_XIONG,

        /**
         * 美术宝1对1
         */
        VIP1V1,

        /**
         * 作品集小程序
         */
        ZPJ_PROGRAM
    }
}
