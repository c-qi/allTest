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

@ApiModel(description = "用户业务对应线表")
@Table(name = "zp_user_business", indexes = {
        @Index(name = "zp_user_id", columnList = "user_id")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpUserBusiness extends BaseEntity {

    @ApiModelProperty("用户ID")
    @Column(name = "user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0+ " COMMENT '用户ID'")
    private Long userId;

    @ApiModelProperty("来源头像")
    @Column(name = "origin_head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '头像'")
    private String originHead = null;

    @ApiModelProperty("用户来源")
    @Column(name = "from_type", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0+ " COMMENT '用户来源'")
    private FROMTYPE fromType;

    @ApiModelProperty("来源用户ID")
    @Column(name = "origin_user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0+ " COMMENT '来源用户ID'")
    private Long originUserId;

    @ApiModelProperty("用户性别")
    @Column(name = "origin_sex", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0+ " COMMENT '用户性别'")
    private ZpUser.SEX originSex;

    @ApiModelProperty("用户生日")
    @Column(name = "origin_birthday", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0+ " COMMENT '用户生日'")
    private Long originBirthday;

    @ApiModelProperty("省")
    @Column(name = "origin_province", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '省'")
    private String originProvince;

    @ApiModelProperty("市")
    @Column(name = "origin_city", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '市'")
    private String originCity;

    @ApiModelProperty("区")
    @Column(name = "origin_area", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '区'")
    private String originArea;

    @ApiModelProperty("来源用户openid")
    @Column(name = "origin_openid", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '来源用户openid'")
    private String originOpenid;

    @ApiModelProperty("来源用户unionId")
    @Column(name = "origin_union_id", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '来源用户unionId'")
    private String originUnionId;

    @ApiModel(description = "来源类型")
    public enum FROMTYPE{
        /**
         * 一对一
         */
        ONETOONE,

        /**
         * 小熊
         */
        BEAR,

        /**
         * 1对1和小熊共同存在
         */
        ONETOONE_BEAR,

        /**
         * 作品集
         */
        WORKS,
    }

}
