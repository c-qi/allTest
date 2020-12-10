package org.zhire.suanfa.practise;

import org.junit.Test;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @Date 2020/12/8 17:29
 * @Author by chenqi
 */
public class Jz48 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        int start = 0;
        int maxLength = 0;
        for (int r = 1; r < s.length(); r++) {
            // 判断是否有重复
            for (int j = start; j < r; j++) {
                if (s.charAt(j) == s.charAt(r)) {
                    //重复，需要重新指定起始位置
                    start = j + 1;
                    break;
                }
            }
            //重新计算长度
            maxLength = Math.max(maxLength, r - start + 1);
        }
        return maxLength;
    }

    @Test
    public void t() {
        System.out.println(lengthOfLongestSubstring("abcabcbda"));
        System.out.println(lengthOfLongestSubstring("abca"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("bbbbbao"));
    }
}
