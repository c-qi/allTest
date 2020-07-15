package org.zhire.suanfa;


import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.6.17 16:16
 */
public class leetcode47 {
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        int[] nums = {1};
        System.out.println(permuteUnique(nums));
    }


    public static List<List<Integer>> permuteUnique(int[] nums) {
        return paixu(nums, 0, nums.length - 1);
    }

    static HashSet set = new HashSet();

    public static List<List<Integer>> paixu(int[] nums, int k, int n) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k == n) {
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[j] + " ");
                list.add(nums[j]);
            }
            set.add(list);
            System.out.println();
        } else {
            for (int i = k; i <= n; i++) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
                paixu(nums, k + 1, n);
                temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
        System.out.println(set);
        List<List<Integer>> resultList = new ArrayList<>();
        for (Object o : set) {
            System.out.println(o);
            resultList.add((List<Integer>) o);
        }
        return resultList;
    }


}
