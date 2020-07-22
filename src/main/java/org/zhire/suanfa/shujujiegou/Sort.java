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
        int[] nums4 = {8, 10, 2, 3, 6, 1, 5};
        // sortBymp(nums);
        // System.out.println();
        // sortByInsert(nums2);
        // System.out.println();
        // sortByXz(nums3);
        //  sortByXz2(nums3);
        //quickSort(nums4);
        sortByQuick(nums4);

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

    /**
     * 快速排序
     *
     * @param nums
     */
    private static void quickSort(int[] nums) {
        quickSortNums(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * @param nums 数组
     * @param p    下标
     * @param r    下标
     */
    private static void quickSortNums(int[] nums, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(nums, p, r); // 找到分区点
        quickSortNums(nums, p, q - 1);
        quickSortNums(nums, p + 1, r);
    }

    private static int partition(int[] nums, int p, int r) {
        int pivot = nums[r]; // 选最后一个为分区点
        int i = p;
        for (int j = p; j < r; ++j) {
            if (nums[j] < pivot) {
                // if (i == j) {
                //   ++i;
                //  } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                // }
            }
        }
        // 把分区点插入
        int tmp = nums[i];
        nums[i] = nums[r];
        nums[r] = tmp;
        System.out.println("i=" + i);
        return i;
    }


    /**
     * 快速排序
     *
     * @param nums
     */
    private static void sortByQuick(int[] nums) {
        sortByQuickMethod(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private static void sortByQuickMethod(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = getMiddle(nums, left, right);
        sortByQuickMethod(nums, left, middle - 1);
        sortByQuickMethod(nums, middle + 1, right);

    }

    private static int getMiddle(int[] nums, int left, int right) {
        int on = nums[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < on) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        int temp = nums[i];
        nums[i] = nums[right];
        nums[right] = temp;
        return i;
    }


}
