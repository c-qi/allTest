package org.zhire.jvm;

/**
 * @Author: chenqi
 * @Date: 2019.9.26 14:59
 */
public class TestException {
    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("hello");
        }
    }
}
