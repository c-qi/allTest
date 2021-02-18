package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 *
 * @Date 2021/2/18 14:11
 * @Author by chenqi
 */
public class Jz58_2 {
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        StringBuilder ss = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i < n) ss.append(chars[i]);
            else sb.append(chars[i]);
        }
        return sb.toString() + ss.toString();
    }

    public String reverseLeftWords2(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    @Test
    public void t() {
        System.out.println(reverseLeftWords("abcdefg", 2));
        System.out.println(reverseLeftWords2("abcdefg", 2));
    }
}
