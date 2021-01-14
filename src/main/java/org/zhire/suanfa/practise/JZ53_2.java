package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 * @Date 2021/1/14 15:44
 * @Author by chenqi
 */
public class JZ53_2 {
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length + 1; i++)
            if (this.bSearch(nums, i) == -1) return i;
        return 0;
    }

    private int bSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] > target) r = m - 1;
            else if (nums[m] < target) l = m + 1;
            else return target;
        }
        return -1;
    }

    @Test
    public void t() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(missingNumber(a));
    }
}
