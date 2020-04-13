package org.zhire.jvm;

/**
 * @Author: chenqi
 * @Date: 2019.9.23 16:06
 */
public class TestBoolean {
    public static void main(String[] args) {
        try {
            boolean flag = true;
            if (flag) {
                System.out.println("hello -java");
            }
            if (flag == true) {
                System.out.println("hello jvm");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
