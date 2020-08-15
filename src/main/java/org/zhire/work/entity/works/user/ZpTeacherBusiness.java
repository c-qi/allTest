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
import javax.persistence.Table;

@ApiModel(description = "作品集老师")
@Table(name = "zp_teacher_business")
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpTeacherBusiness extends BaseEntity {

    @ApiModelProperty("老师Id")
    @Column(name = "teacher_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0+ " COMMENT '老师Id'")
    private Long teacherId;

    @ApiModelProperty("老师来源")
    @Column(name = "from_type", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0+ " COMMENT '老师来源'")
    private ZpUserBusiness.FROMTYPE fromType;

    @ApiModelProperty("来源老师Id")
    @Column(name = "origin_teacher_id", nullable = true, columnDefinition = BaseEntity.BIGINT_DEFAULT_0+ " COMMENT '来源老师Id'")
    private Long originTeacherId;

    @ApiModelProperty("来源老师头像")
    @Column(name = "origin_teacher_head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '来源老师头像'")
    private String originTeacherHead = null;

    @ApiModelProperty("来源老师昵称")
    @Column(name = "origin_teacher_nickname", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '来源老师昵称'")
    private String originTeacherNickname;

    @ApiModelProperty("来源老师真实姓名")
    @Column(name = "origin_teacher_name", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '来源老师真实姓名'")
    private String originTeacherName;

    @ApiModelProperty("年龄")
    @Column(name = "origin_age", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0+ " COMMENT '年龄'")
    private Integer originAge;

    @ApiModelProperty("状态")
    @Column(name = "status", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0+ " COMMENT '状态'")
    private STATUS status;
}
