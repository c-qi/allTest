package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/29 21:42
 * @Author by chenqi
 */
public class Jianzhi14_1 {

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
        int[] ints = new int[n + 1];
        ints[1] = 1;
        ints[2] = 1;
        ints[3] = 2;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // j和i-j都不能再拆了
                int a = j * (i - j);
                // j能拆，i-j不能拆
                int b = ints[j] * (i - j);
                // j不能拆，i-j能拆
                int c = j * ints[i - j];
                // j和i-j都能拆
                int d = ints[j] * ints[i - j];
                // 不分割的情况
                int e = ints[i];
                // 比较上面所有情况的最大值
                ints[i] = Math.max(Math.max(Math.max(a, b), Math.max(c, d)), e);
            }
        }
        System.out.println(Arrays.toString(ints));
        return ints[n];
    }

    /**
     * ① 当所有绳段长度相等时，乘积最大。
     * ② 最优的绳段长度为3。
     * 最优： 3 。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0, 1, 2 三种情况。
     * 次优： 2 。若最后一段绳子长度为 2 ；则保留，不再拆为  1 + 1。
     * 最差： 1 。若最后一段绳子长度为 1 ；则应把一份 3 + 1 替换为 2 + 2，因为 2 * 2 > 3 * 1。
     *
     * @param n
     * @return
     */
    public int cuttingRope3(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }
        int r = 1;
        while (n > 4) {
            // 如果n大于4，我们不停的让他减去3
            n = n - 3;
            // 计算每段的乘积
            r = r * 3;
        }
        return r * n;
    }

    @Test
    public void t() {
        System.out.println(cuttingRope(5));
        System.out.println(cuttingRope3(5));
    }
}
