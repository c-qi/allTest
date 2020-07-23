package org.zhire.suanfa.shujujiegou;

/**
 * 二分查找
 * 时间复杂度是 O(logn)。
 *
 * @Author: chenqi
 * @Date: 2020.7.22 16:51
 */
public class Search {
    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 10};
        int[] nums2 = {1, 1, 3, 4, 5, 6, 8, 8, 8, 8, 11, 18, 18};
        //  System.out.println(bsearch(nums, 10));
        //  System.out.println(bsearch2(nums, 3, 0, nums.length - 1));
        //  System.out.println(sqrt(5.0));
        System.out.println(bsearchFirst(nums2, 18));
        System.out.println(bsearchLast(nums2, 18));
        System.out.println(bsearchFirstSameOrBigger(nums2, 2));
        System.out.println(bsearchLastSameOrBigger(nums2, 2));
    }


    /**
     * 二分查找非递归
     *
     * @param nums
     * @param value
     * @return
     */
    private static int bsearch(int[] nums, int value) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int middle = (start + last) / 2;
            if (nums[middle] == value) {
                return nums[middle];
            } else if (nums[middle] < value) {
                start = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找递归
     *
     * @param nums
     * @param value
     * @param start
     * @param last
     * @return
     */
    private static int bsearch2(int[] nums, int value, int start, int last) {
        if (start > last) {
            return -1;
        }
        int middle = (start + last) / 2;
        if (nums[middle] == value) {
            return nums[middle];
        } else if (nums[middle] < value) {
            start = middle + 1;
            return bsearch2(nums, value, start, last);
        } else {
            last = middle - 1;
            return bsearch2(nums, value, start, last);
        }
    }


    /**
     * 查找第一个值等于给定值的元素
     *
     * @param nums
     * @param value
     * @return
     */
    private static int bsearchFirst(int[] nums, int value) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int middle = (start + last) / 2;
            if (nums[middle] == value) {
                if (middle == 0 || nums[middle - 1] != value)
                    return middle;
                else {
                    start = middle + 1;
                }
            } else if (nums[middle] < value) {
                start = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     *
     * @param nums
     * @param value
     * @return
     */
    private static int bsearchLast(int[] nums, int value) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int middle = (start + last) / 2;
            if (nums[middle] == value) {
                if (middle == nums.length - 1 || nums[middle + 1] != value)
                    return middle;
                else {
                    start = middle + 1;
                }
            } else if (nums[middle] < value) {
                start = middle + 1;
            } else {
                last = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param nums
     * @param value
     * @return
     */
    private static int bsearchFirstSameOrBigger(int[] nums, int value) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int middle = (start + last) / 2;
            if (nums[middle] >= value) {
                if (middle == 0 || nums[middle - 1] < value) {
                    return middle;
                } else {
                    last = middle - 1;
                }
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param nums
     * @param value
     * @return
     */
    private static int bsearchLastSameOrBigger(int[] nums, int value) {
        int start = 0;
        int last = nums.length - 1;
        while (start <= last) {
            int middle = (start + last) / 2;
            if (nums[middle] > value) {
                last = middle - 1;
            } else {
                if (middle == nums.length - 1 || nums[middle + 1] > value) {
                    return middle;
                } else {
                    start = middle + 1;
                }

            }
        }
        return -1;
    }

    /**
     * 求一个数的开平方
     *
     * @param n
     * @return
     */
    private static double sqrt(double n) {
        double start = 0;
        double last = n;
        double middle = (start + last) / 2;
        while (start <= last) {
            if (middle * middle > n) {
                last = middle * middle;
            } else if (middle * middle < n) {
                start = middle * middle;
            } else {
                return middle;
            }
        }
        return -1;
//        double base = 0;
//        int l = 0, r = 9;
//        double step = 10;
//        for (int i = 0; i < 7; i++) {
//            step *= 0.1;
//            while (l <= r) {
//                int middle = l + (r - l) / 2;
//                if (Math.pow((base + middle * step), 2) == n) {
//                    return middle;
//                }
//                if (Math.pow((base + middle * step), 2) < n) {
//                    l = middle + 1;
//                }
//                if (Math.pow((base + middle * step), 2) > n) {
//                    r = middle - 1;
//                }
//            }
//            base += (r) * step;
//            l = 0;
//            r = 9;
//        }
//        return base;


        //初始化上界和下届
//        double low = 0;
//        double high = n;
//        double mid = low + (high - low) / 2;
//        //给定精度
//        while (high - low > 1e-7) {
//            if (mid * mid > n)
//                high = mid;
//            else if (mid * mid < n)
//                low = mid;
//            else if (Math.abs(mid * mid - n) < 1e-7) {
//                return mid;
//            }
//            mid = low + (high - low) / 2;
//        }
//        return mid;
    }


}
