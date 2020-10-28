package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 求x的n次方
 *
 * @Date 2020/10/28 15:12
 * @Author by chenqi
 */
public class Jianzhi16 {
    @Test
    public void t() {
        //System.out.println(myPow(2.1, 3));
        System.out.println(myPow2(2.1, 3));
    }

    /**
     * 2 2
     * 2*2
     * 2 3
     * 2*2*2
     *
     * @param x
     * @param n
     * @return
     */
    private double myPow(double x, int n) {
        long nn = n;
        if (x == 1) {
            return 1;
        }
        boolean f = false;
        if (nn < 0) {
            f = true;
            nn = Math.abs(nn);
        }
        double r = 1;
        for (long i = 0; i < nn; i++) {
            r = r * x;
        }
        if (f) {
            if (r == 0) {
                return 0;
            }
            r = 1 / r;
        }
        return r;
    }


    private double myPow2(double x, int n) {
        // 如果n等于0，直接返回1
        if (n == 0) {
            return 1;
        }
        // 如果n小于0，把它改为正数
        if (n < 0) {
            return myPow2(1 / x, -n);
        }
        // 根据n是奇数还是偶数来做不同的处理
        return (n % 2 == 0) ? myPow2(x * x, n / 2) : x * myPow2(x * x, n / 2);
    }


}