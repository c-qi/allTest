package org.zhire.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Date 2021/2/27 15:02
 * @Author by chenqi
 */
@Getter
@AllArgsConstructor
public enum MyNewEnum implements Serializable {

    OK("0", "OK"),
    ERROR("1", "ERROR");

    private String code;
    private String type;


}
