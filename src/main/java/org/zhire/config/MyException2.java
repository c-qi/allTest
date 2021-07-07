package org.zhire.config;

import lombok.Data;

/**
 * @Date 2020/12/28 15:26
 * @Author by chenqi
 */
@Data
public class MyException2 extends Exception {

    private Integer code;

    public MyException2(int code, String message) {
        super(message);
        this.code = code;
    }

}
