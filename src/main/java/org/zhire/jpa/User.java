package org.zhire.jpa;

import lombok.Data;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
@ApiObject // 　JSONDoc注解
public class User implements Serializable{

    @Id
    @GeneratedValue
    @ApiObjectField(description = "ID") // JSONDoc注解
    private Long id;

    @Column(nullable = false, unique = true)
    @ApiObjectField(description = "姓名")
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String nickName;



}