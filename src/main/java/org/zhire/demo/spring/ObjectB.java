package org.zhire.demo.spring;

import lombok.Data;

/**
 * @Author: chenqi
 * @Date: 2020.7.30 09:10
 */
@Data
public class ObjectB {
    private ObjectA a;

    public ObjectA getA() {
        return a;
    }


}
