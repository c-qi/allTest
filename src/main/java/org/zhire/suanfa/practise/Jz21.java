package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * @Date 2020/11/3 16:37
 * @Author by chenqi
 */
public class Jz21 {
    public int[] exchange(int[] nums) {
        int[] result = new int[nums.length];
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                list.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        list.addAll(list2);
        Object[] objects = list.toArray();
        for (int i = 0; i < objects.length; i++) {
            result[i] = (int) objects[i];
        }
        return result;
    }

    @Test
    public void t() {
        int[] a = {1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange(a)));
    }
}
