package org.zhire.utils;

import com.google.common.base.Joiner;

import java.util.LinkedList;

public class NumberChangeUtils {

    private static final String num = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 根据当前32进制数获取下一个32进制数
     *
     * @param number
     * @return
     */
    public static String getNextNum(String number) {
        LinkedList result = new LinkedList();
        char[] cs = number.toCharArray();
        boolean needAddOne = true;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (needAddOne) {
                char c = cs[i];
                if (num.indexOf(c) == num.length() - 1) {
                    cs[i] = '0';
                } else {
                    cs[i] = num.charAt(num.indexOf(c) + 1);
                    needAddOne = false;
                }
            }
            result.addFirst(cs[i]);
        }
        if (needAddOne) {
            result.addFirst('1');
        }
        return Joiner.on("").join(result);
    }

}
