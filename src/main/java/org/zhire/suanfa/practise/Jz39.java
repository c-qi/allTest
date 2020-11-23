package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 * <p>
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * @Date 2020/11/23 16:50
 * @Author by chenqi
 */
public class Jz39 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int m = nums.length / 2;
        AtomicInteger r = new AtomicInteger();
        map.forEach((k, v) -> {
            if (v > m) {
                r.set(k);
            }
        });
        return r.get();
    }

    @Test
    public void t() {
        int[] a = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(a));
    }
}
