package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * @Date 2020/11/25 17:08
 * @Author by chenqi
 */
public class Jz45 {
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        System.out.println(list);
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
    }

    @Test
    public void t() {
        int[] a = {1, 3, 4, 66, 234};
        System.out.println(minNumber(a));
    }

}
