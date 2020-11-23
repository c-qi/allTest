package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 * @Date 2020/11/23 17:15
 * @Author by chenqi
 */
public class Jz40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] r = new int[k];
        System.arraycopy(arr, 0, r, 0, k);
        return r;
    }

    @Test
    public void t() {
        int[] a = {3, 2, 1};
        System.out.println(Arrays.toString(getLeastNumbers(a, 3)));

    }
}
