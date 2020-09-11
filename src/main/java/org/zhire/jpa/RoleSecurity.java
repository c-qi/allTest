package org.zhire.jpa;

import lombok.Data;
import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_role")
@ApiObject // 　JSONDoc注解
public class RoleSecurity implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;



}