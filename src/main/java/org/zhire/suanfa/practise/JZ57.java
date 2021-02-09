package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10] * @Date 2021/2/9 16:38
 *
 * @Author by chenqi
 */
public class JZ57 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int bSearch = bSearch(nums, target - nums[i]);
            if (bSearch != -1) {
                result[0] = nums[i];
                result[1] = bSearch;
                return result;
            }
        }
        return result;
    }


    public int bSearch(int[] nums, int s) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int middle = (l + r) / 2;
            if (nums[middle] > s) {
                r = middle - 1;
            } else if (nums[middle] < s) {
                l = middle + 1;
            } else {
                return s;
            }
        }
        return -1;

    }

    @Test
    public void t() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 26)));
    }
}
