package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 二进制中1的个数
 *
 * @Date 2020/10/28 14:44
 * @Author by chenqi
 */
public class Jianzhi15 {
    @Test
    public void t() {
        System.out.println(hammingWeight(9));
    }

    public int hammingWeight(int n) {
        return Integer.toBinaryString(n).replace("0", "").length();
//        char[] chars = string.toCharArray();
//        int flag = 0;
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] == '1') {
//                flag += 1;
//            }
//        }
//        return flag;
    }
}
