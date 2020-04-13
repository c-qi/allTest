package org.zhire.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenqi
 * @Date: 2019.4.16 17:27
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0000);
        put("message", "success");
    }

    public static R error() {
        return error(StatusCodes.ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(StatusCodes.ERROR, msg);
    }

    public static R error(String code, String message) {
        R r = new R();
        r.put("code", code);
        r.put("message", message);
        return r;
    }

    public static R ok(String message) {
        R r = new R();
        r.put("message", message);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}