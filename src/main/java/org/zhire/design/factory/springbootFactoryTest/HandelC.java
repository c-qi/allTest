package org.zhire.design.factory.springbootFactoryTest;

import org.springframework.stereotype.Service;

/**
 * @Date 2021/5/11 18:26
 * @Author by chenqi
 */
@Service
public class HandelC implements Handel {
    @Override
    public String handel() {
        return "C";
    }
}
