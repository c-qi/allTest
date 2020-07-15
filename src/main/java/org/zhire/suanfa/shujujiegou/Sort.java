package org.zhire.suanfa.shujujiegou;


/**
 * @Author: chenqi
 * @Date: 2020.7.15 14:50
 */
public class Sort {
    public static void main(String[] args) {
        int[] nums = {3, 2, 5, 4, -1, 0, 9, 9, -2, 100, 30};
        int[] nums2 = {3, 2, 5, 4, -1, 0, 9, 9, -2, 100, 30};
        int[] nums3 = {3, 2, 5, 4, -1, 0, 9, 9, -2, 100, 30};
        sortBymp(nums);
        System.out.println();
        sortByInsert(nums2);
        System.out.println();
        sortByXz(nums3);
        sortByXz2(nums3);

    }


    /**
     * 冒泡排序
     * <p>
     * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，
     * 看是否满足大小关系要求。如果不满足就让它俩互换。
     * 一次冒泡会让至少一个元素移动到它应该在的位置，
     * 重复 n 次，就完成了 n 个数据的排序工作。
     * 时间复杂度O(n^2)
     *
     * @param nums
     */
    private static void sortBymp(int[] nums) {
        // 选最大的放右边
        // 32541
        // 23541
        // 23541
        // 23451
        // 23415
        for (int i = 0; i < nums.length - 1; i++) { // 循环控制交换的轮数
            for (int j = 0; j < nums.length - 1 - i; j++) { // 循环控制每轮需要交换的次数
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 插入排序
     * <p>
     * 将数组中的数据分为两个区间，已排序区间和未排序区间。
     * 初始已排序区间只有一个元素，就是数组的第一个元素。
     * 插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，
     * 并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束
     * 3 2 5 4
     * 2 3 5 4
     * 2 3 5 4
     * 2 3 4 5
     *
     * @param nums
     */
    private static void sortByInsert(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            int value = nums[i];
            int j = i - 1; // 查找插入的位置
            for (; j >= 0; --j) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j]; // 数据移动
                } else {
                    break;
                }
            }
            nums[j + 1] = value; // 插入数据
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 选择排序
     *
     * @param nums
     */
    private static void sortByXz(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i; // 注意 min = i 不是 0，默认每一轮排序第一个数据为最小然后比较
            for (int j = i; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            // 数组的i和最小值min处的值交换 注意是在内循环外面
            int temp = nums[min];
            nums[min] = nums[i];
            nums[i] = temp;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * 选择排序2
     * <p>
     * 32541
     * 23541
     * 23541
     * 23541
     * 12354
     *
     * @param nums
     */
    private static void sortByXz2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }


}
