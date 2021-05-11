package org.zhire.demo.test;

import org.springframework.context.annotation.Configuration;

/**
 * @Date 2021/5/11 14:18
 * @Author by chenqi
 */
@Configuration
public interface InterfaceTest {


    String getName();

    static Integer getAge(Integer a) {
        return a + 1;
    }

    default String getAddress(Integer a) {
        return "北京";
    }

}
