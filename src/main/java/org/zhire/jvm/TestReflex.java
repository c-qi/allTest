package org.zhire.jvm;

import java.lang.reflect.Method;

/**
 * @Author: chenqi
 * @Date: 2019.9.25 14:22
 */
public class TestReflex {

    public void test() {

    }

    public static void main(String[] args) throws Exception {
        Class<?> testReflex = Class.forName("org.zhire.jvm.TestReflex");
        Method[] methods = testReflex.getMethods();
        for (Method method : methods) {
            System.out.println(method);

        }

    }
}
