package org.zhire.design.strategy;

/**
 * @Author: chenqi
 * @Date: 2019.7.24 20:57
 */

public class Test {
    public static void main(String[] args) {
        Context context = new Context(new Add());
        System.out.println("1 + 1 = " + context.exe(1, 1));

        context = new Context(new Del());
        System.out.println("1 - 1 = " + context.exe(1, 1));

        context = new Context(new Mul());
        System.out.println("1 * 1 = " + context.exe(1, 1));

    }
}
