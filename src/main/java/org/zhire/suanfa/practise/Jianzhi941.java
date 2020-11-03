package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 941. 有效的山脉数组
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * <p>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * <p>
 * A.length >= 3
 * 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 示例 1：
 * <p>
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[1,2,3,4,5,4,3,2,1]
 * 输出：true
 *
 * @Date 2020/11/3 11:08
 * @Author by chenqi
 */
public class Jianzhi941 {
    @Test
    public void t() {
        int[] a = {0, 2, 4, 5, 6};
        System.out.println(validMountainArray(a));

    }

    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int[] b = A.clone();
        Arrays.sort(b);
        int max = b[b.length - 1];
        int maxFlag = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == max) {
                maxFlag = i;
            }
        }
        if (maxFlag == A.length - 1 || maxFlag == 0) {
            return false;
        }
        System.out.println(maxFlag);
        for (int i = 0; i <= maxFlag; i++) {
            for (int j = i + 1; j <= maxFlag; j++) {
                System.out.println(i + " " + j);
                if (A[j] <= A[i]) {
                    return false;
                } else {
                    break;
                }
            }
        }
        for (int i = maxFlag; i <= A.length - 1; i++) {
            for (int j = i + 1; j <= A.length - 1; j++) {
                if (A[j] >= A[i]) {
                    return false;
                } else {
                    break;
                }
            }
        }
        return true;
    }


}
