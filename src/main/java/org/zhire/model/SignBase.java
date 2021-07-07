package org.zhire.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2021/6/30 11:05 上午
 * @Author chenqi
 */
@Data
public class SignBase implements Serializable {
    private String sign;
    private String times;
}
