package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * @Date 2020/11/14 14:37
 * @Author by chenqi
 */
public class Jz1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] c1 = arr1.clone();
        int[] c2 = arr2.clone();
        Arrays.sort(c1);
        Arrays.sort(c2);
        // 数字 出现次数
        HashMap<Integer, Integer> hashMap2 = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (hashMap2.containsKey(arr1[i])) hashMap2.put(arr1[i], hashMap2.get(arr1[i]) + 1);
            else hashMap2.put(arr1[i], 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            if (Arrays.binarySearch(c2, c1[i]) < 0) list.add(c1[i]);
        }
        Object[] array = list.toArray();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < arr2.length; i++) {
            int n = arr2[i];
            for (int j = 0; j < hashMap2.get(n); j++) {
                sb.append(n).append(",");
            }
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(",");
        }
        String s = sb.substring(0, sb.lastIndexOf(","));
        String[] strings = s.split(",");
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    @Test
    public void t() {
        int[] a = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] b = {2, 1, 4, 3, 9, 6};
        System.out.println(Arrays.toString(relativeSortArray(a, b)));
    }
}
