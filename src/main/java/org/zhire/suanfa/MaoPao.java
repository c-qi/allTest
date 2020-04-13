package org.zhire.suanfa;

import java.util.Arrays;

/**
 * 冒泡排序就是重复“从序列左边开始比较相邻两个数字的大小，再根据结果交换两个数字
 * 的位置”这一操作的算法。在这个过程中，数字会像泡泡一样，慢慢从左往右“浮”到序列的
 * 顶端，所以这个算法才被称为“冒泡排序”。
 * 在冒泡排序中，第 1 轮需要比较 n-1 次，第 2 轮需要比较 n-2 次……第 n-1 轮需
 * 要比较 1 次。因此，总的比较次数为 (n-1)+(n-2)+…+1 ≈ n 2 /2。这个比较次数恒定为
 * 该数值，和输入数据的排列顺序无关。
 * 不过，交换数字的次数和输入数据的排列顺序有关。假设出现某种极端情况，如输
 * 入数据正好以从小到大的顺序排列，那么便不需要任何交换操作；反过来，输入数据要
 * 是以从大到小的顺序排列，那么每次比较数字后便都要进行交换。因此，冒泡排序的时
 * 间复杂度为 O(n 2 )。
 *
 * @Author: chenqi
 * @Date: 2020.2.26 19:01
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] array = {5, 9, 3, 1, 2, 8, 4, 7, 6};
        // 外层控制循环次数
        for (int i = 0; i < array.length - 1; i++) {
            // 内层控制每循环对比次数1-8 2-7 3-6 4-5
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));


    }

}
