package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * @Date 2020/11/2 21:04
 * @Author by chenqi
 */
public class Jianzhi17 {
    @Test
    public void t() {
        printNumbers(3);
    }

    public int[] printNumbers(int n) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < n; i++) {
            sb.append("9");
        }
        int size = Integer.parseInt(sb.toString());
        int[] r = new int[size];
        for (int i = 1; i <= size; i++) {
            r[i - 1] = i;
        }
        return r;
    }
}

