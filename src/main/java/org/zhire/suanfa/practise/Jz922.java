package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 922. 按奇偶排序数组 II
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * @Date 2020/11/12 14:23
 * @Author by chenqi
 */
public class Jz922 {
    public int[] sortArrayByParityII(int[] A) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int aA : A) {
            if (aA % 2 == 0) {
                list.add(aA);
            } else {
                list2.add(aA);
            }
        }
        int ou = 0, ji = 0;
        for (int i = 0; i < A.length; i++) {
            if (i % 2 == 0) {
                A[i] = list.get(ou);
                ou++;
            } else {
                A[i] = list2.get(ji);
                ji++;
            }
        }
        return A;
    }

    @Test
    public void t() {
        int[] a = {4, 2, 5, 7};
        System.out.println(Arrays.toString(sortArrayByParityII(a)));
    }
}
