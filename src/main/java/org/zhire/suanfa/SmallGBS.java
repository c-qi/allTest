package org.zhire.suanfa;

/**
 * 最大公约数
 * 最小公倍数
 *
 * @Author: chenqi
 * @Date: 2020.6.22 09:06
 */
public class SmallGBS {
    public static void main(String[] args) {
        int m = 8;
        int n = 12;
        int max = 0, min = 0; // 定义最大公约数、最小公倍数
        if (m > n) // 将m记为两数间的较小值
        {
            int t = m;
            m = n;
            n = t;
        }
        for (int i = 1; i <= m; i++) {
            if (m % i == 0 & n % i == 0)
                max = i;
        }
        min = m * n / max;
        System.out.println("最大公约数为:" + max + ",最小公倍数为：" + min);

    }
}
