package org.zhire.suanfa.shujujiegou;

import java.util.Arrays;

/**
 * 1。自定义数组--支持增加，删，改，查
 * 2。实现一个大小固定的有序数组，支持动态增删改操作
 * 3。实现两个有序数组合并为一个有序数组
 *
 * @Author: chenqi
 * @Date: 2020.7.6 09:00
 */
public class MyArray {

    // 定义整型数据data保存数据
    private int[] date;
    // 数组的长度
    private int length;
    // 数组的实际长度
    private int count;

    /**
     * 数组的初始化
     *
     * @param length
     */
    public MyArray(int length) {
        date = new int[length];
        this.length = length;
        this.count = 0;
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "date=" + Arrays.toString(date) +
                ", length=" + length +
                ", count=" + count +
                '}';
    }

    /**
     * 数组打印
     */
    public void printMyArray() {

        for (int i = 0; i < count; i++) {
            System.out.println(date[i]);
        }
        System.out.println();
    }

    /**
     * 数组的插入
     *
     * @param index  索引
     * @param number 值
     */
    public void add(int index, int number) {
        if (count == length) {
            throw new RuntimeException("array已满，无法继续存储");
        }
        if (index > count || index < 0) {
            throw new RuntimeException("索引异常，位置不合法");
        }
        this.date[index] = number;
        count++;
    }

    /**
     * 数据插入并排序
     * 选择排序
     *
     * @param number
     */
    public void addBySort(int number) {
        if (count == length) {
            throw new RuntimeException("array已满，无法继续存储");
        }
        this.date[count] = number;
        count++;
        sort();
    }

    private void sort() {
        System.out.println("排序前：");
        printMyArray();
        for (int i = 0; i < count; i++) {
            int min = i;
            for (int j = min; j < count; j++) {
                if (date[min] > date[j]) {
                    min = j;
                }
            }
            // t--记录新的最小值
            int t = date[min];
            // 遍历到j的值 等于 外出循环位置
            date[min] = date[i];
            // 最外面循环值等于最小值
            date[i] = t;
        }
        System.out.println("排序后：");
        printMyArray();

    }

    /**
     * 数组的删除
     *
     * @param index 索引
     */
    public void remove(int index) {
        if (index > count || index < 0) {
            throw new RuntimeException("索引异常，位置不合法");
        }
        // 从删除位置开始，将后面的元素向前移动一位
        for (int i = index + 1; i < count; i++) {
            // System.out.println(date[i]);
            date[i - 1] = date[i];
        }
        count--;
    }

    /**
     * 数组的删除 支持排序
     *
     * @param index 索引
     */
    public void removeBySort(int index) {
        remove(index);
        // sort();

    }

    /**
     * 数组的更新
     *
     * @param index  索引
     * @param numbre 值
     */
    public void update(int index, int numbre) {
        if (index > count || index < 0) {
            throw new RuntimeException("索引异常，位置不合法");
        }
        date[index] = numbre;
    }

    /**
     * 数组的查找
     *
     * @param index 索引
     * @return
     */
    public int find(int index) {
        if (index > count || index < 0) {
            throw new RuntimeException("索引异常，位置不合法");
        }
        System.out.println(date[index]);
        return date[index];
    }

    // 两个排序数组，合并成一个新的排序数组
    public static void countArrayBySort(MyArray my1, MyArray my2) {
        // 合并两个排序数组
        int[] array = new int[my1.count + my1.count];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (i < my1.count) {
                array[i] = my1.date[i];
            } else {
                array[i] = my2.date[j];
                j++;
            }
        }
        // 新数组排序
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int k = min; k < array.length; k++) {
                if (array[min] > array[k]) {
                    min = k;
                }
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        // 新数组打印
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);

        }

    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(6);
        myArray.add(0, 1);
        myArray.add(1, 2);
        myArray.add(2, 3);
        myArray.add(3, 4);
        myArray.add(4, 5);
        myArray.printMyArray();
        System.out.println("-----------------");
        myArray.remove(1);
        System.out.println("-----------------");
        myArray.printMyArray();
        myArray.find(3);
        myArray.update(3, 55);
        myArray.find(3);
        System.out.println("===============");
        MyArray mySortArray = new MyArray(3);
        mySortArray.addBySort(2);
        mySortArray.addBySort(1);
        mySortArray.addBySort(-1);
        //  mySortArray.removeBySort(3);
        MyArray mySortArray2 = new MyArray(3);
        mySortArray2.addBySort(0);
        mySortArray2.addBySort(100);
        mySortArray2.addBySort(99);
        countArrayBySort(mySortArray, mySortArray2);

    }
}
