package org.zhire.jvm;

import org.zhire.pojo.User;

/**
 * @Author: chenqi
 * @Date: 2019.8.29 09:18
 */
public class Test {
    private static int a = 1;

    public static void main(String[] args) {
//        int a = 1;
//        int b = 2;
//        int c = a + b;
//        print();
//        System.out.println(c);
        while (true) {
            GcTest test = new GcTest();
            System.out.println(test);
            System.out.println("====================================");

            User user = new User();
            System.out.println(user);
            System.out.println("====================================");
        }
    }

    public static void print() {
       // System.out.println(GcTest.aaa);
    }

    @org.junit.Test
    public void s() {
        s();
    }
}
