package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.*;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.1.19 14:24
 */
public class leetcode7 {
    @Test
    public void test() {
        int reverse = test2(1534236469);
        // int reverse = reverse(1534236469);
        System.out.println(reverse);

    }

    public int reverse(int x) {
        String value = String.valueOf(x);
        String substring = "";
        StringBuilder after = new StringBuilder();
        if (x < 0) {
            substring = value.substring(1, value.length());
            after.append("-");
        }
        if (x > 0) {
            substring = value;
        }
        if (x == 0) {
            return 0;
        }


        char[] chars = substring.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            after.append(c);
        }
        long l = Long.parseLong(after.toString());
        if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(after.toString());
    }

    public int test2(int x) {
        Stack stack = new Stack();
        String value = String.valueOf(x);
        String substring = "";
        StringBuilder after = new StringBuilder();
        if (x < 0) {
            substring = value.substring(1, value.length());
            after.append("-");
        }
        if (x > 0) {
            substring = value;
        }
        if (x == 0) {
            return 0;
        }
        char[] chars = substring.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
        }
        System.out.println(stack);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            Object pop = stack.pop();
            after.append(pop);
        }
        long l = Long.parseLong(after.toString());
        if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(after.toString());
    }

}
