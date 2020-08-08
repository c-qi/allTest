package org.zhire.jpa;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userAddress;


    @Column(nullable = false)
    private Integer userId;



}