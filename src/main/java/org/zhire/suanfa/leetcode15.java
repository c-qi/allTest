package org.zhire.suanfa;

import io.swagger.models.auth.In;
import org.assertj.core.util.Lists;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.6.17 16:16
 */
public class leetcode15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum2(nums));
    }

    /**
     * 越写越离谱...
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        if (nums[0] == 0 && nums[1] == 0 && nums[2] == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<List<Integer>> lists = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            lists.add(list);
            return lists;
        }
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        HashSet<Integer> set = new HashSet<>();
        HashSet<int[]> zeroSet = new HashSet<>();
        HashMap<Object, Object> map = new HashMap<>();
        for (int num : nums) {
            set.add(num);
            if (map.containsKey(num)) {
                Integer i = (Integer) map.get(num);
                map.put(num, i + 1);
            } else {
                map.put(num, 1);
            }
        }
        System.out.println(map);
        Set<Object> set1 = map.keySet();
        for (Object o : set1) {
            System.out.println(o + " : " + map.get(o));

        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int flag = nums[i] + nums[j];
                int zero = 0 - flag;
                System.out.println("i:" + i + " j:" + j + " " + zero);
                if (set.contains(zero)) {
                    int[] zeroNums = new int[3];
                    zeroNums[0] = nums[i];
                    zeroNums[1] = nums[j];
                    zeroNums[2] = zero;
                    Arrays.sort(zeroNums);

                    zeroSet.add(zeroNums);

                    break;
                }
            }

        }

        class Three {
            int one;
            int two;
            int three;

            public int getOne() {
                return one;
            }

            public int getTwo() {
                return two;
            }

            public int getThree() {
                return three;
            }

            public Three(int one, int two, int three) {
                this.one = one;
                this.two = two;
                this.three = three;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Three three1 = (Three) o;
                return one == three1.one &&
                        two == three1.two &&
                        three == three1.three;
            }

            @Override
            public int hashCode() {
                return Objects.hash(one, two, three);
            }

        }
        HashSet<Three> objects = new HashSet<>();
        for (int[] ints : zeroSet) {
            Three three = new Three(ints[0], ints[1], ints[2]);
            objects.add(three);
        }
        System.out.println(objects);
        for (Three object : objects) {
            List<Integer> objects1 = new ArrayList<>();
            objects1.add(object.getOne());
            objects1.add(object.getTwo());
            objects1.add(object.getThree());
            if (object.getOne() == object.getTwo()) {
                if (Integer.parseInt(map.get(object.getOne()).toString()) > 1) {
                    list.add(objects1);
                    continue;
                }
            }
            if (object.getThree() == object.getTwo()) {
                if (Integer.parseInt(map.get(object.getThree()).toString()) > 1) {
                    list.add(objects1);
                    continue;
                }

            }
            if (object.getThree() == object.getOne()) {
                if (Integer.parseInt(map.get(object.getThree()).toString()) > 1) {
                    list.add(objects1);
                    continue;
                }

            }
            if (object.getThree() != object.getOne() &&
                    object.getTwo() != object.getOne() &&
                    object.getTwo() != object.getThree()) {
                list.add(objects1);
                continue;
            }


        }
        return list;

    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) {
            return ans;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++; // 去重
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }

}
