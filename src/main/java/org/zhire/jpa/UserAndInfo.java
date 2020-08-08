package org.zhire.jpa;

import lombok.Data;


@Data
public class UserAndInfo {
    private Long userInfoId;

    private String userAddress;

    private Integer userId;

    private Long id;

    private String userName;

    private String passWord;

    private String email;

    private String nickName;
}
