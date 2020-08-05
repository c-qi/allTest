package org.zhire.suanfa.shujujiegou;

/**
 * 字符串匹配
 *
 * @Auther chenqi
 * @Date 2020/8/5
 */
public class StringSearch {
    public static void main(String[] args) {
        System.out.println(bF("cbdheabcd", "bcd"));
    }


    /**
     * BF暴力匹配
     *
     * @param a
     * @param b
     * @return
     */
    public static int bF(String a, String b) {
        int m = a.length(), n = b.length(), k;
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        for (int i = 0; i <= m - n; i++) {
            k = 0;
            for (int j = 0; j < n; j++) {
                if (a1[i + j] == b1[j]) {
                    k++;
                } else
                    break;
            }
            if (k == n) {
                return i;
            }
        }
        return -1;
    }

}

