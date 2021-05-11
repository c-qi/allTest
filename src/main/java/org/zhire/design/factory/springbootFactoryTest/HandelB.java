package org.zhire.design.factory.springbootFactoryTest;

import org.springframework.stereotype.Service;

/**
 * @Date 2021/5/11 18:26
 * @Author by chenqi
 */
@Service
public class HandelB implements Handel {
    @Override
    public String handel() {
        return "B";
    }
}
