package org.zhire.controller;

import lombok.Data;

@Data
public class UserExcel {
    Long id;
    String  email;
    String password;
    LEVEL level;
    enum LEVEL{
        ORDINARY,
        VIP,
        SVIP
    }
}
