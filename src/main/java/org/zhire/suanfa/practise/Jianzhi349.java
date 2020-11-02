package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * @Date 2020/11/2 18:28
 * @Author by chenqi
 */
public class Jianzhi349 {
    @Test
    public void t() {
        int[] a = {1, 2, 3, 45, 4};
        int[] b = {2, 3, 4, 2};
        System.out.println(Arrays.toString(intersection(a, b)));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list2.add(nums2[i]);
        }
        list.forEach(l -> {
            if (list2.contains(l)) {
                set.add(l);
            }
        });
        int[] r = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < r.length; i++) {
            r[i] = iterator.next();
        }
        return r;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (int i = 0; i < nums1.length; i++) {
            int result = bSearch(nums2, nums1[i]);
            if (-1 != result) {
                set.add(result);
            }
        }
        int[] r = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < r.length; i++) {
            r[i] = iterator.next();
        }
        return r;
    }

    // 二分法
    private int bSearch(int[] nums, int num) {
        int left = 0;
        int right = nums.length - 1;
        int middle = (left + right) / 2;
        while (left <= right) {
            if (nums[middle] == num) {
                return num;
            } else if ((nums[middle] > num)) {
                right = middle - 1;
                middle = (left + right) / 2;
            } else {
                left = middle + 1;
                middle = (left + right) / 2;
            }
        }
        return -1;
    }

    @Test
    public void yy() {
        int[] a = {1, 2, 3, 45, 49, 50};
        int[] b = {2, 3, 4, 2};
        System.out.println(Arrays.toString(intersection2(a, b)));
    }
}
