package org.zhire.utils;

import com.google.common.base.Joiner;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
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
    public static void main(String[] args) {
        String limitCountKey = keyBuilder("KEY", DateTime.now().toString("yyyyMMdd"), "17777823345");
        System.out.println(limitCountKey);
    }
    /**
     * redis的key键规则定义
     * @param prefix
     * @param args
     * @return
     */
    public static String keyBuilder(@NotNull String prefix, String... args) {
        StringBuilder key = new StringBuilder(prefix);
        for (String arg : args) {
            key.append(":").append(arg);
        }
        return key.toString();
    }
}
