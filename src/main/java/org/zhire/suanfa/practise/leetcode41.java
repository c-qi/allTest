package org.zhire.suanfa.practise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.6.17 16:16
 */
public class leetcode41 {
    public static void main(String[] args) {

        int[] nums = {};
        System.out.println(firstMissingPositive(nums));

    }


    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        if (nums.length == 1 && nums[0] == 0) {
            return 1;
        }

        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        if (max < 0) {
            return 1;
        }
        List list = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        System.out.println(list);
        System.out.println();
        int result = 0;
        for (int i = 1; i <= max; i++) {
            if (!list.contains(i)) {
                result = i;
                break;
            } else {
                result = i + 1;
            }
        }
        return result;
    }
}
