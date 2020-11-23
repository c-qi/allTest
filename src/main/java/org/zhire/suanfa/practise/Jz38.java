package org.zhire.suanfa.practise;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * @Date 2020/11/23 16:06
 * @Author by chenqi
 */
public class Jz38 {

    HashSet<String> set = new HashSet();

    private void perm(String list[], int k, int m) {
        if (k == m) { // 没有到最后一个   所以还需要进行排列
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < list.length; i++) {
                System.out.print(list[i]);
                sb.append(list[i]);
            }
            set.add(sb.toString());
            System.out.println();
        } else { // 进行排列
            for (int i = k; i <= m; i++) {
                String c = list[i]; // 进行交换  从而完成全排列
                list[i] = list[k];
                list[k] = c;
                perm(list, k + 1, m);
                c = list[i]; // 需要交换回来 不然就会乱了顺序
                list[i] = list[k];
                list[k] = c;
            }
        }
    }

    public String[] permutation(String s) {
        char[] array = s.toCharArray();
        String[] ss = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            ss[i] = String.valueOf(array[i]);
        }
        perm(ss, 0, ss.length - 1);
        String[] r = new String[set.size()];
        int i = 0;
        for (String s1 : set) {
            r[i] = s1;
            i++;
        }
        return r;
    }

    @Test
    public void t() {
        String ss = "xbjjlvjb";
        System.out.println(Arrays.toString(permutation(ss)));
    }

}
