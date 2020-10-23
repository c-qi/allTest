package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 输入：n = 0 输出：1
 * 输入：n = 1 输出：1
 * 输入：n = 2 输出：2
 * 输入：n = 3 输出：3
 * 输入：n = 4 输出：5
 * 输入：n = 5 输出：8
 * 输入：n = 6 输出：13
 * 输入：n = 7 输出：21
 * 即求值f(n) = f(n-1) + f(n-2)
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/23 14:04
 * @Author by chenqi
 */
public class Jianzhi10_2 {

    // 递归法
    private int numWays(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return (numWays(n - 1) + numWays(n - 2)) % 1000000007;
    }

    // 动态规划
    private int numWays2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 2;
        for (int i = 3; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
            nums[i] = nums[i] % 1000000007;
        }
        return nums[n];
    }

    @Test
    public void test() {
        //  System.out.println(numWays(7));
        System.out.println(numWays2(100));
    }
}
