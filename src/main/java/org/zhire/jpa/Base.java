package org.zhire.jpa;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;



@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    //@Column(updatable = false, nullable = false)
    @Column(name = "ctime", nullable = true, columnDefinition = "bigint default 0")
    private Long ctime;

    @ApiModelProperty("修改时间")
    @LastModifiedDate
    @Column(name = "utime", nullable = true, columnDefinition = "bigint default 0")
    private Long utime;
}
