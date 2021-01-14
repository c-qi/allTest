package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * @Date 2021/1/14 14:37
 * @Author by chenqi
 */
public class JZ53 {

    /**
     * 常规解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int result = 0;
        for (int num : nums) {
            if (num == target) {
                result++;
            }
        }
        return result;
    }

    /**
     * 二分法
     * 先算出目标数的位置
     * 然后在目标位置前后遍历数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int result = 0, l = 0, r = nums.length - 1, flag = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] > target)
                r = m - 1;
            else if (nums[m] < target)
                l = m + 1;
            else {
                flag = m;
                break;
            }
        }
        for (int i = flag; i >= 0; i--)
            if (nums[i] == target) result++;
            else break;
        for (int i = flag + 1; i < nums.length; i++)
            if (nums[i] == target) result++;
            else break;
        return result;
    }


    @Test
    public void t() {
        int[] a = {8, 8, 8, 8, 8, 8};
        System.out.println(search(a, 8));
        System.out.println(search2(a, 8));
    }
}
