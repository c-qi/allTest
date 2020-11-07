package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 2 <= n <= 1000
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/29 21:42
 * @Author by chenqi
 */
public class Jianzhi14_2 {

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }
        long[] ints = new long[n + 1];
        ints[1] = 1;
        ints[2] = 1;
        ints[3] = 2;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                long a = (j * (i - j));
                long b = (ints[j] * (i - j));
                long c = (j * ints[(i - j)]);
                long d = (ints[j] * ints[i - j]);
                long e = (ints[i]);
                ints[i] = Math.max(Math.max(Math.max(a, b), Math.max(c, d)), e) % 1000000007;
            }
        }
        return Integer.parseInt(ints[n] + "");
    }


    @Test
    public void t() {
        System.out.println(cuttingRope(120));
    }
}
