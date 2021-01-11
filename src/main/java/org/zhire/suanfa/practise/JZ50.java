package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例:
 * <p>
 * s = "abaccdeff"
 * 返回 "b"
 * <p>
 * s = ""
 * 返回 " "
 *
 * @Date 2021/1/9 18:31
 * @Author by chenqi
 */
public class JZ50 {
    public char firstUniqChar(String s) {
        if (null == s || "".equals(s)) return ' ';
        if (s.length() == 1) return s.charAt(0);
        char[] array = s.toCharArray();
        HashMap<Character, Integer> hashMap = new LinkedHashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (null == hashMap.get(array[i]))
                hashMap.put(array[i], 1);
            else hashMap.put(array[i], hashMap.get(array[i]) + 1);
        }
        System.out.println("hashMap:" + hashMap);

        ArrayList<Object> list = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            if (v == 1) {
                System.out.println(k);
                list.add(k);
            }
        });
        Character o = ' ';
        if (!list.isEmpty())
            o = (Character) list.get(0);
        return o;
    }

    @Test
    public void t() {
        System.out.println(firstUniqChar("cc"));
    }


}
