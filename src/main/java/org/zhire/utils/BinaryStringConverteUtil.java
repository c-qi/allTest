package org.zhire.utils;

/**
 * 二进制转换工具类
 */
public class BinaryStringConverteUtil {
    private static final String BIN_SEPARATOR = " ";

    /**
     * 字符串转换为二进制字符串
     *
     * @param str 普通字符串
     * @return String 二进制字符串
     */
    public static String toBinaryString(String str) {

        if (str == null) return null;

        StringBuffer sb = new StringBuffer();

        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            sb.append(Integer.toBinaryString(aByte) + BIN_SEPARATOR);
        }
        return sb.toString();
    }


    /**
     * 二进制字符串转换为普通字符串
     *
     * @param binaryStr 二进制字符串
     * @return String 普通字符串
     */
    public static String toString(String binaryStr) {

        if (binaryStr == null) return null;

        String[] binArrays = binaryStr.split(BIN_SEPARATOR);


        StringBuffer sb = new StringBuffer();
        for (String binStr : binArrays) {
            char c = binstrToChar(binStr);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 统计二进制字符串中1的个数
     *
     * @param binaryStr 二进制字符，如:1101
     * @return int
     */
    public static int countBitOne(String binaryStr) {
        int cnt = 0;
        if (binaryStr != null) {
            byte[] bytes = binaryStr.getBytes();
            for (byte aByte : bytes) {
                if (aByte == 49) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * 二进制字符转换为int数组
     *
     * @param binStr 二进制字符串
     * @return int[]
     */
    private static int[] binstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    /**
     * 将二进制转换成字符
     *
     * @param binStr 二进制字符串
     * @return char
     */
    private static char binstrToChar(String binStr) {
        int[] temp = binstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    public static void main(String[] args) {
        String str = "a";
        // 转换为二进制字符串
        String binaryStr = BinaryStringConverteUtil.toBinaryString(str);
        // 获取1的数量
        int cnt = BinaryStringConverteUtil.countBitOne(binaryStr);
        // 反向转换
        String newStr = BinaryStringConverteUtil.toString("1100001");
        System.out.println("源字符串:" + str);
        System.out.println("二进制字符串:" + binaryStr);
        System.out.println("1位数量:" + BinaryStringConverteUtil.countBitOne(binaryStr));
        System.out.println("新字符串:" + newStr);
    }
}
