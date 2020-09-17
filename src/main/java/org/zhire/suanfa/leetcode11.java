package org.zhire.suanfa;

import java.util.HashMap;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.06.15
 */
public class leetcode11 {
    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        maxArea(arr);

    }

    public static int maxArea(int[] height) {
        // 本人简单粗暴解法 算出每种情况 选择最大
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > height[i]) {
                    int max = height[i] * (j - i);
                    boolean b = null == map.get("max") ? true : false;
                    if (b) {
                        map.put("max", max);
                    } else {
                        if (map.get("max") < max) {
                            map.put("max", max);
                        }
                    }
                } else {
                    int max = height[j] * (j - i);
                    boolean b = null == map.get("max") ? true : false;
                    if (b) {
                        map.put("max", max);
                    } else {
                        if (map.get("max") < max) {
                            map.put("max", max);
                        }
                    }
                }
            }
        }
        System.out.println(map.get("max"));
        //return map.get("max");

        // 官方解法 双指针
        // 1, 8, 6, 2, 5, 4, 8, 3, 7
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        System.out.println(ans);
        return ans;
    }
}

