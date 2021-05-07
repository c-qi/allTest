package org.zhire.demo.test;

/**
 * @Date 2021/4/30 17:02
 * @Author by chenqi
 */
public class ExtensTest {
    public static void main(String[] args) {

        get(MovieFather.class);

    }

    private static void get(Class<?> clazz) {

        if (clazz.isAssignableFrom(MovieFather.class)){
            System.out.println("hhh");
        }

    }
}
