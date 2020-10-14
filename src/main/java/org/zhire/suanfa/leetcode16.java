package org.zhire.suanfa;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * 给定一个数组，要求在这个数组中找出 3 个数之和离 target 最近。
 */
public class leetcode16 {
    @Test
    public void lengthOfLongestSubstring() {
        int[] nums = {-1, 2, 1, -4, 23, 1524, 63456, 876};
        int target = 1000000;
        System.out.println(get(nums, target));
        System.out.println(threeSumClosest(nums, target));
    }


    private int get(int[] nums, int target) {
        StopWatch sp = new StopWatch();
        sp.start();
        int flag = -1;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int abs = Math.abs(target - sum);
                    if (flag < 0 || flag > abs) {
                        flag = abs;
                        result = sum;
                    }
                }
            }
        }
        sp.stop();
        System.out.println("one:" + sp.getTotalTimeSeconds());
        return result;
    }

    public int threeSumClosest(int[] nums, int target) {
        StopWatch sp = new StopWatch();
        sp.start();
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    sp.stop();
                    System.out.println("two:" + sp.getTotalTimeSeconds());
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        sp.stop();
        System.out.println("two:" + sp.getTotalTimeSeconds());
        return best;
    }


}

