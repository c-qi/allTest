package org.zhire.jpa;

import lombok.Data;
import org.jsondoc.core.annotation.ApiObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "t_user")
@ApiObject // 　JSONDoc注解
public class UserSecurity implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private Integer available;




}