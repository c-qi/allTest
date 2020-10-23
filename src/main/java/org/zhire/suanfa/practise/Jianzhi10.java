package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 0 <= n <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/23 14:04
 * @Author by chenqi
 */
public class Jianzhi10 {
    // 递归法 耗时
    private int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int r = (fib(n - 1) + fib(n - 2)) % 1000000007;
        return r;
    }

    // 循环求余法
    private int fib2(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    // 动态规划法
    private int fib3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
            // 循环取余，保证值不会越界
            nums[i] = nums[i] % 1000000007;
        }
        return nums[n];

    }


    @Test
    public void test() {
        System.out.println(fib(48));
        System.out.println(fib2(10));
        System.out.println("\n" + fib3(10));
    }
}
