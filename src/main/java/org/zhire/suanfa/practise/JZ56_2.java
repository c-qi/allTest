package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * @Date 2021/2/9 14:31
 * @Author by chenqi
 */
public class JZ56_2 {

    public int singleNumber(int[] nums) {
        AtomicInteger r = new AtomicInteger();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) map.put(nums[i], 1);
            else if (map.get(nums[i]) == 1) map.put(nums[i], map.get(nums[i]) + 1);
            else map.remove(nums[i]);
        }
        map.forEach((k, v) -> {
            r.set(k);
        });
        return r.get();
    }

    @Test
    public void test() {
        System.out.println((singleNumber(new int[]{9, 1, 7, 9, 7, 9, 7})));
    }
}
