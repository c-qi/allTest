package org.zhire.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class CopyUserOne {

    private Long id;

//    @Alias("userName")
    private String userName;

    @Alias("userAge")
    private String userAge;

    private String password;

    private String country;

    private String city;

    private String area;

    private String address;

    private String mobile;

    private String email;

    private String qq;

    private String wechat;

    private String sex;

    private String workAddress;

    private String workYear;

    private String favorite;

    private String book;

    private String fruit;

    private String color;

    private String animal;

}
