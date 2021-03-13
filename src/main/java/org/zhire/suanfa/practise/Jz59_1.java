package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *
 * @Date 2021/3/11 17:43
 * @Author by chenqi
 */
public class Jz59_1 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < i + k; j++) {
                list.add(nums[j]);
                if (list.size() == k) {
                    System.out.println(list);
                    List<Integer> collect = list.parallelStream().sorted().collect(Collectors.toList());
                    result.add(collect.get(collect.size() - 1));
                    list.clear();
                }
            }
            if (i >= nums.length - k)
                break;
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            r[i] = result.get(i);
        return r;
    }

    @Test
    public void t() {
        int[] ints = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int a = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(ints, a)));
    }
}
