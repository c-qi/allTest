package org.zhire.config;

import lombok.Data;

/**
 * @Date 2020/12/28 15:26
 * @Author by chenqi
 */
@Data
public class MyException extends RuntimeException {

    private Integer code;

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

}
