package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @Date 2021/2/18 11:42
 * @Author by chenqi
 */
public class JZ58 {
    public String reverseWords(String s) {
        String trim = s.trim();
        String[] s1 = trim.split(" ");
        System.out.println(Arrays.toString(s1));
        StringBuffer sb = new StringBuffer();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (s1[i].trim().isEmpty()) continue;
            sb.append(s1[i]).append(" ");
        }
        return sb.toString().trim();
    }

    @Test
    public void t() {
        System.out.println(reverseWords("  hello  world!  "));
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("a good   example"));
    }
}
