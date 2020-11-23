package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 示例1:c
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * @Date 2020/11/23 17:37
 * @Author by chenqi
 */
public class Jz42 {

    /**
     * 暴力法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                System.out.println("i:" + i);
                System.out.println("j:" + j);
                if (max == 0 && j - i == 1) max = nums[i] + nums[j];
                else max += nums[j];
                set.add(max);
                System.out.println("max:" + max);
                System.out.println();
            }
        }
        return set.stream().sorted().collect(Collectors.toList()).get(set.size() - 1);
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    @Test
    public void t() {
        int[] a = {1, 2, 3, 4};
        System.out.println(maxSubArray(a));
        System.out.println(maxSubArray2(a));

    }
}
