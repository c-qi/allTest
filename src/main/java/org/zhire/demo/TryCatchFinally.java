package org.zhire.demo;

/**
 * @Author: chenqi
 * @Date: 2020.6.10 17:27
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println("last:"+test());

    }

    private static String test() {
        try {
            int i = 1 / 0;
            System.out.println("try");
            return "try";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch");
            // catch里面有return语句时先不执行 先执行finally内代码
            return "catch";
        } finally {
            System.out.println("finally");
            return "finally";
        }
    }
}
