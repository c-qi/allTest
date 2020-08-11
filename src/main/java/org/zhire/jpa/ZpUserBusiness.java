package org.zhire.jpa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;

@ApiModel(description = "用户表")
@Table(name = "zp_user_business", indexes = {
        @Index(name = "zp_user_id", columnList = "user_id")
})
@Entity
@Data
@DynamicInsert(true)
@DynamicUpdate(true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class ZpUserBusiness {

    @Id
    @GeneratedValue
    @ApiObjectField(description = "ID") // JSONDoc注解
    private Long id;

    @ApiModelProperty("用户ID")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty("用户来源")
    @Column(name = "from_type")
    private FROMTYPE fromType;

    @ApiModel(description = "来源类型")
    public enum FROMTYPE{

        TEST,

        WORKS,
    }

}
