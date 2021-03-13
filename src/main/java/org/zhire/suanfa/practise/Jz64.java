package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 求1+2+3+...+n
 *
 * @Date 2021/3/12 17:36
 * @Author by chenqi
 */
public class Jz64 {
    public int sumNums(int n) {
        return (1 + n) * n / 2;
    }

    @Test
    public void t() {
        System.out.println(sumNums(3));
    }
}
