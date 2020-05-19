package org.zhire.suanfa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.1.19 14:24
 */
public class leetcode3 {
    @Test
    public void lengthOfLongestSubstring() {
        String s = "pwwkew";
        // int i = lengthOfLongestSubstring(s);
        int i2 = lengthOfLongestSubstring2(s);
        int i3 = lengthOfLongestSubstring3(s);
        System.out.println(i2);

    }

    private int lengthOfLongestSubstring3(String s) {
        HashSet set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    break;
                }
                set.add(chars[j]);
                set.add(String.valueOf(chars[j]));
            }
        }

        return set.size();
    }
    //  6 5 4
    //  6 4 3


    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<String> set = new LinkedHashSet();
        for (int i = 0; i < chars.length; i++) {
            set.add(String.valueOf(chars[i]));
        }
        String str = "";
        for (String o : set) {
            str += o;
        }
        if (s.contains(str) && set.size() != 1) {
            return set.size();
        } else if (set.size() == 1) {
            return 1;
        } else if (!s.contains(str)) {
            return set.size() - 1;
        } else {
            return set.size();
        }

    }

    // dvdf
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


}

