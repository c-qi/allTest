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
@Table(name = "zp_teacher")
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpTeacher extends BaseEntity {

    @ApiModelProperty("老师昵称")
    @Column(name = "teacher_nickname", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '老师昵称'")
    private String teacherNickname;

    @ApiModelProperty("老师头像")
    @Column(name = "teacher_head", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0 + " COMMENT '老师头像'")
    private String teacherHead = null;

    @ApiModelProperty("老师真实姓名")
    @Column(name = "teacher_name", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '老师真实姓名'")
    private String teacherName;

    @ApiModelProperty("手机号")
    @Column(name = "mobile", nullable = true, columnDefinition = BaseEntity.VARCHAR_DEFAULT_0+ " COMMENT '手机号'")
    private String mobile;

    @ApiModelProperty("年龄")
    @Column(name = "age", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0+ " COMMENT '年龄'")
    private Integer age;

    @ApiModelProperty("状态")
    @Column(name = "status", nullable = true, columnDefinition = BaseEntity.TINY_INT_DEFAULT_0+ " COMMENT '状态'")
    private STATUS status;
}
