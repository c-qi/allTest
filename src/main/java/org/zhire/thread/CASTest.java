package org.zhire.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger底层CAS实现的
 *
 * @Author: chenqi
 * @Date: 2019.12.3 10:30
 */
public class CASTest {
    static AtomicInteger integer = new AtomicInteger();

    public static void main(String[] args) {
        Thread r = new Thread() {
            @Override
            public void run() {
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread r1 = new Thread() {
            @Override
            public void run() {
                try {
                    test();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        r.start();
        r1.start();

    }


    static void test() {
        for (int i = 0; i < 10; i++) {
            boolean b = integer.compareAndSet(1, 1);
            System.out.println(b);
        }
    }
    @Test
    public void test1() {

    }

}
