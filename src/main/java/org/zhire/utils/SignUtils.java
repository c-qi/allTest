package org.zhire.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Date 2021/3/24 17:32
 * @Author by chenqi
 */
@Slf4j
public class SignUtils {

    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String SIGN_METHOD_MD5 = "md5";
    private static final String SIGN_METHOD_HMAC = "hmac";
    private static final String SIGN_METHOD_HMAC_MD5 = "HmacMD5";

    /**
     * 验签方法
     *
     * @param request 调用服务 request
     * @param secret  密钥
     * @return
     * @throws IOException
     */
    public static CheckResult checkSign(HttpServletRequest request, String secret)
            throws IOException {

        CheckResult checkResult = new CheckResult();
        Map<String, String> paramMap = getQueryMap(request, CHARSET_UTF8);
        log.info("map:{}", JSON.toJSONString(paramMap));
        String body = getStreamAsString(request.getInputStream(), CHARSET_UTF8);
        if (!StringUtils.isEmpty(body)) {
            Map bodyMap = JSON.parseObject(body, Map.class);
            paramMap.putAll(bodyMap);
        }

        log.info("sign paramMap:{} body:{}", JSON.toJSONString(paramMap), body);
        String remoteSign = paramMap.remove("sign");
        String signMethod = paramMap.get("signMethod");
        if (StringUtils.isEmpty(signMethod)) {
            signMethod = SIGN_METHOD_MD5;
        }

        String localSign = signRequest(paramMap, secret, signMethod);
        Boolean result = localSign.equals(remoteSign);
        checkResult.setSuccess(result);
        checkResult.setRequestBody(body);

        return checkResult;
    }

    public static CheckResult checkSign2(HttpServletRequest request, String secret, Map<String, String> bodyMap)
            throws IOException {
        CheckResult checkResult = new CheckResult();
        Map<String, String> paramMap = getQueryMap(request, CHARSET_UTF8);
        log.info("map:{}", JSON.toJSONString(paramMap));
//        String body = getStreamAsString(request.getInputStream(), CHARSET_UTF8);
        paramMap.putAll(bodyMap);
//        log.info("sign paramMap:{} body:{}", JSON.toJSONString(paramMap), body);
        String remoteSign = paramMap.remove("sign");
        String signMethod = paramMap.get("signMethod");
        if (StringUtils.isEmpty(signMethod)) {
            signMethod = SIGN_METHOD_MD5;
        }
        String localSign = signRequest(paramMap, secret, signMethod);
        Boolean result = localSign.equals(remoteSign);
        checkResult.setSuccess(result);
        checkResult.setRequestBody(bodyMap.toString());
        return checkResult;
    }

    public static String signRequest(Map<String, String> params, String secret, String signMethod)
            throws IOException {

        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (SIGN_METHOD_MD5.equalsIgnoreCase(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                query.append(key).append(value);
            }
        }

        // 第三步：使用MD5/HMAC加密
        byte[] bytes;
        if (SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else {
            query.append(secret);
            bytes = encryptMD5(query.toString());
        }

        // 第四步：把二进制转化为大写的十六进制(正确签名应该为32大写字符串，此方法需要时使用)
        return byte2hex(bytes);
    }

    public static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), SIGN_METHOD_HMAC_MD5);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }


    public static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes(CHARSET_UTF8));
    }


    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static byte[] encryptMD5(byte[] data) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance(SIGN_METHOD_MD5.toUpperCase());
            byte[] bytes = md.digest(data);
            return bytes;
        } catch (GeneralSecurityException var3) {
            throw new IOException(var3.toString());
        }
    }

    public static Map<String, String> getQueryMap(HttpServletRequest request, String charset)
            throws IOException {
        Map<String, String> queryMap = new HashMap();
        String queryString = request.getQueryString();
        String[] params = queryString.split("&");

        for (int i = 0; i < params.length; ++i) {
            String[] kv = params[i].split("=");
            String key;
            if (kv.length == 2) {
                key = URLDecoder.decode(kv[0], charset);
                String value = URLDecoder.decode(kv[1], charset);
                queryMap.put(key, value);
            } else if (kv.length == 1) {
                key = URLDecoder.decode(kv[0], charset);
                queryMap.put(key, "");
            }
        }
        return queryMap;
    }

    @Data
    public static class CheckResult {

        private Boolean success;
        private String requestBody;
    }


    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();
            char[] buff = new char[1024];
            boolean var5 = false;

            int read;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            String var6 = response.toString();
            return var6;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("method", "check");
        map.put("time_range", "1234123f");
        map.put("appid", "1234");
        map.put("timestamp", 1552298843);
        map.put("start_time", "2015-12-01 17:00:00");
        System.out.println(JSON.toJSONString(map));
        String secret = "hello";
        StringBuffer sb = new StringBuffer(secret);
        map.forEach((k,v)->{
             sb.append(k).append(v);
        });
        sb.append(secret);
        System.out.println(sb);
        String md5 = MD5Util.getMD5(sb.toString());
        System.out.println(md5);

    }
}
