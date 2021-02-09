package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.*;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 * @Date 2021/2/9 14:31
 * @Author by chenqi
 */
public class JZ56 {

    public int[] singleNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] r = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) map.put(nums[i], 1);
            else map.remove(nums[i]);
        }
        map.forEach((k, v) -> {
            list.add(k);
        });
        r[0] = list.get(0);
        r[1] = list.get(1);
        return r;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(singleNumbers(new int[]{4, 1, 4, 6})));
    }
}
