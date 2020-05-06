package org.zhire.suanfa;

import java.util.Arrays;

/**
 * 选择排序就是重复“从待排序的数据中寻找最小值，将其与序列最左边的数字进行交换”
 * 这一操作的算法。在序列中寻找最小值时使用的是线性查找。
 * 选择排序使用了线性查找来寻找最小值，因此在第 1 轮中需要比较 n-1 个数字，第
 * 2 轮需要比较 n-2 个数字……到第 n-1 轮的时候就只需比较 1 个数字了。因此，总的比
 * 较次数与冒泡排序的相同，都是 (n-1)+(n-2)+…+1 ≈ n 2 /2 次。
 * 每轮中交换数字的次数最多为 1 次。如果输入数据就是按从小到大的顺序排列的，
 * 便不需要进行任何交换。选择排序的时间复杂度也和冒泡排序的一样，都为 O(n 2 )。
 *
 *
 *
 * @Author: chenqi
 * @Date: 2020.2.26 19:01
 */
public class XuanZhe {
    public static void main(String[] args) {
        int[] array = {9, 5, 3, 1, 2, 8, 4, 7, 6};
        // 1, 5, 3, 9, 2, 8, 4, 7, 6
        // 1, 2, 3, 9, 5, 8, 4, 7, 6
        // 1, 2, 3, 9, 5, 8, 4, 7, 6
        // 1, 2, 3, 4, 5, 8, 9, 7, 6
        // ......
//        for (int i = 0; i < array.length; i++) {
//            // 最小数的数组下标
//            int minimum = i;
//            for (int j = i; j < array.length; j++) {
//                if (array[j] < array[minimum]) {
//                    // 找到最小数的下标并更新
//                    minimum = j;
//                }
//            }
//            // 交换符合条件的数
//            int temp = array[minimum];
//            array[minimum] = array[i];
//            array[i] = temp;
//
//        }
//        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = min; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));

    }

}
