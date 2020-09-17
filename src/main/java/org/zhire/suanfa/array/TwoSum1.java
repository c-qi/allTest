package org.zhire.suanfa.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 在数组中找到 2 个数之和等于给定值的数字，结果返回 2 个数字在数组中的下标。
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1]
 *
 * @author chenqi
 */
public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));


    }

    /**
     * 暴力破解法
     * 所有数字相加和目标值比对
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 好点的解法，时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 先算出预期想要的值
            int another = target - nums[i];
            // 如果map里面存的有当前遍历的值，表示该值就是预期想要的值，可以直接取
            if (map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
            }
            // 如果map里面没有，就把预期值和下标存入
            map.put(another, i);
        }
        System.out.println(map);
        return result;

    }


}
