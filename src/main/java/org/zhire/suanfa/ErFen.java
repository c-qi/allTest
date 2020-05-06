package org.zhire.suanfa;

/**
 * 二分查找
 * 每次取中间位置的值与待查关键字比较，如果中间位置
 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字
 *
 * @Author: chenqi
 * @Date: 2020.4.23 16:23
 */
public class ErFen {
    public static void main(String[] args) {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int i = biSearch(array, 8);
        System.out.println(i);
    }

    public static int biSearch(int[] array, int a) {
//        int lo = 0;
//        int hi = array.length - 1;
//        int mid;
//        while (lo <= hi) {
//            mid = (lo + hi) / 2; // 中间位置
//            if (array[mid] == a) {
//                return mid + 1;
//            } else if (array[mid] < a) { // 向右查找
//                lo = mid + 1;
//            } else { // 向左查找
//                hi = mid - 1;
//            }
//        }
        int start = 0;
        int length = array.length - 1;
        int middle;
        while (start <= length) {
            middle = start + length / 2;
            if (array[middle] == a) {
                return middle + 1;
            } else if (array[middle] < a) {
                start = middle + 1;
            } else {
                length = middle - 1;
            }

        }
        return -1;
    }
}
