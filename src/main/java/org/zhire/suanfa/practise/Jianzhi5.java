package org.zhire.suanfa.practise;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * @Date 2020/10/15 20:23
 * @Author by chenqi
 */
public class Jianzhi5 {
    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));

    }

    public static String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
