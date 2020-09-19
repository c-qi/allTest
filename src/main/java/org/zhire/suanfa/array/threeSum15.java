package org.zhire.suanfa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum15 {
    public static void main(String[] args) {
        int[] nums = {0,0,0};
        threeSum3(nums);
    }

    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int zero = nums[i] + nums[j] + nums[k];
                    if (zero == 0) {
                        Integer[] a = new Integer[3];
                        a[0] = nums[i];
                        a[1] = nums[j];
                        a[2] = nums[k];
                        ArrayList<Integer> toList = new ArrayList<>();
                        for (List<Integer> integers : list) {
                            if (integers.contains(a[0])
                                    && integers.contains(a[1]) &&
                                    integers.contains(a[2])) {
                                System.out.println(true);
                                continue;
                            } else {
                                toList = new ArrayList<Integer>(Arrays.asList(a));
                                // toList = CollUtil.toList(a);
                            }
                        }
                        System.out.println(toList);
                        list.add(toList);
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isEmpty()) {
                list.remove(i);
            }
        }
        System.out.println(list);
        return list;
    }
}
