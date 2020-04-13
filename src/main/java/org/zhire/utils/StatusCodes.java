package org.zhire.utils;

/**
 * @Author: chenqi
 * @Date: 2019.4.16 17:28
 */
public class StatusCodes {
    /**
     * 处理成功(0000)
     */
    public static String SUCCESS = "0000";

    /**
     * 处理失败(0000)
     */
    public static String FAIL = "0001";

    /**
     * 处理异常(0000)
     */
    public static String ERROR = "0002";
    /**
     * 参数为null
     */
    public static String IS_NULL = "0003";
    /**
     * 唯一性参数重复
     */
    public static String IS_REPEAT = "0004";
    /**
     * 参数过长
     */
    public static String IS_EXCESSLENGTH = "0005";

    /**
     * 参数不符合规范
     */
    public static String NON_CONFORMITY = "0006";
    /**
     * 数据不存在
     */
    public static String NON_EXISTENT = "0007";

    /**
     * 数据不允许删除(0008)
     */
    public static String DATA_NOT_ALLOW_DELETE = "0008";

    /**
     * 数据不存在(0009)
     */
    public static String INVOICE_NOT_FOUND = "0009";

    /**
     * 响应数据为空(0010)
     */
    public static String RESPONSE_IS_NULL = "0010";

    /**
     * 数据状态禁用(0011)
     */
    public static String IS_DISABLE = "0011";

}
