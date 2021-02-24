package org.zhire.service;

/**
 * @Date 2021/2/24 11:53
 * @Author by chenqi
 */
public interface DTOConvert<S, T> {
    T convert(S s);
}
