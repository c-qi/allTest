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

@ApiModel(description = "作品")
@Table(name = "zp_works", indexes = {
        @Index(name = "zp_worksUserId", columnList = "user_id")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpWorks extends BaseEntity {

    @ApiModelProperty("作品作者")
    @Column(name = "user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '作品作者'")
    private Long userId;

    @ApiModelProperty("作品名称")
    @Column(name = "works_name", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品名称'")
    private String worksName;

    @ApiModelProperty("作品分类（水彩，国画等）")
    @Column(name = "works_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '作品分类（水彩，国画等）'")
    private TYPE worksType;

    @ApiModelProperty("作品类型（图片，视频）")
    @Column(name = "works_info_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '作品类型（图片，视频）'")
    private WORKSINFOTYPE worksInfoType;

    @ApiModelProperty("作品原图片或视频地址")
    @Column(name = "works_origin_url", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品原图片或视频地址'")
    private String worksOriginUrl;

    @ApiModelProperty("作品图片 无边框")
    @Column(name = "works_image", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '作品图片 无边框'")
    private String worksImage;

    @ApiModelProperty("作品图片宽")
    @Column(name = "works_image_width", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品图片宽'")
    private String worksImageWidth;

    @ApiModelProperty("作品图片高")
    @Column(name = "works_image_height", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品图片高'")
    private String worksImageHeight;

    @ApiModelProperty("作品视频")
    @Column(name = "works_video", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品图片高'")
    private String worksVideo;

    @ApiModelProperty("作品视频时长秒数")
    @Column(name = "works_video_second", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品视频时长秒数'")
    private String worksVideoSecond;

    @ApiModelProperty("音频描述地址")
    @Column(name = "works_sound_desc_url", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '音频描述地址'")
    private String worksSoundDescUrl;

    @ApiModelProperty("音频时长秒")
    @Column(name = "works_sound_second", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '音频时长秒'")
    private Integer worksSoundSecond;

    @ApiModelProperty("文字描述")
    @Column(name = "works_desc", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '文字描述'")
    private String worksDesc;

    @ApiModelProperty("视频作品封面图地址")
    @Column(name = "works_video_image_url", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '视频作品封面图地址'")
    private String worksVideoImageUrl;

    @ApiModelProperty("视频作品封面图宽")
    @Column(name = "works_video_image_width", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '视频作品封面图宽'")
    private String worksVideoImageWidth;

    @ApiModelProperty("视频作品封面图高")
    @Column(name = "works_video_image_height", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '视频作品封面图高'")
    private String worksVideoImageHeight;

    @ApiModelProperty("作品带相框地址")
    @Column(name = "works_image_box", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框地址'")
    private String worksImageBox;

    @ApiModelProperty("作品带相框宽")
    @Column(name = "works_image_box_width", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框宽'")
    private String worksImageBoxWidth;

    @ApiModelProperty("作品带相框高")
    @Column(name = "works_image_box_height ", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框高'")
    private String worksImageBoxHeight;

    @ApiModelProperty("作品带相框带二维码地址")
    @Column(name = "works_image_box_qr", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框带二维码地址'")
    private String worksImageBoxQr;

    @ApiModelProperty("作品带相框带二维码宽")
    @Column(name = "works_image_box_qr_width", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框带二维码宽'")
    private String worksImageBoxQrWidth;

    @ApiModelProperty("作品带相框带二维码高")
    @Column(name = "works_image_box_qr_height ", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '作品带相框带二维码高'")
    private String worksImageBoxQrHeight;

    @ApiModelProperty("作品来源")
    @Column(name = "works_from_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '作品来源'")
    private FROMTYPE worksFromType;


    @ApiModelProperty("作品原始ID")
    @Column(name = "origin_works_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '作品原始ID'")
    private Long originWorksId;

    @ApiModelProperty("作品原始用户ID")
    @Column(name = "origin_user_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '作品原始用户ID'")
    private Long originUserId;

    @ApiModelProperty("原始创建时间")
    @Column(name = "origin_create_time", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '原始创建时间'")
    private Long originCreateTime;

    @ApiModelProperty("作品年龄")
    @Column(name = "works_age", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0)
    private Integer worksAge;

    @ApiModelProperty("上传作品时省份")
    @Column(name = "province", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '上传作品时省份'")
    private String province;

    @ApiModelProperty("上传作品时城市")
    @Column(name = "city", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '上传作品时城市'")
    private String city;

    @ApiModelProperty("上传作品时区域")
    @Column(name = "area", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '上传作品时区域'")
    private String area;

    @ApiModelProperty("纬度")
    @Column(name = "lat", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '纬度'")
    private Double lat;

    @ApiModelProperty("经度")
    @Column(name = "lng", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '经度'")
    private Double lng;

    @ApiModelProperty("喜欢数量")
    @Column(name = "like_count", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '喜欢数量'")
    private Integer likeCount;

    @ApiModelProperty("首次点赞时间")
    @Column(name = "like_first_time", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '首次点赞时间'")
    private Long likeFirstTime;

    @ApiModelProperty("作品集评论数量")
    @Column(name = "works_comment_count", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '作品集评论数量'")
    private Integer worksCommentCount;
    @ApiModelProperty("首次评论时间")
    @Column(name = "comment_first_time ", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '首次评论时间'")
    private Long commentFirstTime;

    @ApiModelProperty("点评数量")
    @Column(name = "reviews_count", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '点评数量'")
    private Integer reviewsCount;

    @ApiModelProperty("首次点评时间")
    @Column(name = "reviews_first_time ", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0 + " COMMENT '首次点评时间'")
    private Long reviewsFirstTime;

    @ApiModelProperty("作品状态 0无效，1审核中，有效")
    @Column(name = "status", nullable = true, columnDefinition = BaseEntity.INT_DEFAULT_0 + " COMMENT '作品状态0无效，1审核中，有效'")
    private APPROVAL_STATUS status;

    @ApiModelProperty("图片地址是否是在oss上0：否，1：是")
    @Column(name = "image_is_oss", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0 + " COMMENT '图片地址是否是在oss上0：否，1：是'")
    private IS_OSS imageIsOss;

    @ApiModel(description = "来源类型")
    public enum FROMTYPE {
        /**
         * 默认
         */
        DEFAULT,

        /**
         * 一对一
         */
        ONETOONE,

        /**
         * 小熊
         */
        BEAR,

        /**
         * 作品集
         */
        WORKS,
    }

    @ApiModel(description = "作品类型")
    public enum TYPE {
        /**
         * 儿创
         */
        CHILD,

        /**
         * 卡漫
         */
        KAMAN,

        /**
         * 彩铅
         */
        COLOR_LEAD,

        /**
         * 国画
         */
        CHINESE_PAINTING,

        /**
         * 色彩
         */
        COLOR,

        /**
         * 造型
         */
        SKETCH,

        /**
         * 其它
         */
        OTHERS,
    }

    @ApiModel(description = "作品类型")
    public enum WORKSINFOTYPE {
        /**
         * 默认
         */
        DEFAULT,
        /**
         * 图片
         */
        PICTURE,

        /**
         * 视频
         */
        VIDEO
    }

    @ApiModel(description = "作品状态 0无效，1审核中，有效")
    public enum APPROVAL_STATUS {
        /**
         * 无效
         */
        INVALID,

        /**
         * 审核中
         */
        AUDIT,

        /**
         * 有效
         */
        ACTIVE,

    }

    @ApiModel(description = "图片地址是否是在oss上 0：否，1：是")
    public enum IS_OSS {
        /**
         * 否
         */
        NO,

        /**
         * 是
         */
        YES
    }

}
