package org.zhire.jvm;

/**
 * @Author: chenqi
 * @Date: 2019.9.27 15:04
 */
public class MemoryTest {

    private static volatile int a;

    public static void main(String[] args) {

        new Thread(() -> {
            a = 1;
            System.out.println(Thread.currentThread().getName() + "00000:" + a);
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "11111:" + a);
        }).start();

    }




}
