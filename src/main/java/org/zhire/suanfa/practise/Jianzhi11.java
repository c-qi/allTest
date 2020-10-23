package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Date 2020/10/23 16:24
 * @Author by chenqi
 */
public class Jianzhi11 {

    // 排序返回第一个元素
    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    // 循环找出最小的元素返回
    public int minArray2(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[i]) {
                    return numbers[j];
                }
            }
        }
        return numbers[0];
    }

    @Test
    public void test() {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(minArray(nums));
        System.out.println(minArray2(nums));

    }


}
