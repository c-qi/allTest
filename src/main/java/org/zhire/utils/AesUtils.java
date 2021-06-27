package org.zhire.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2021/1/23 15:10
 * @Author by chenqi
 */
public class AesUtils {

    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content 待加密的内存
     * @param key     加密key（长度要超过16）
     * @return
     */
    public static String encrypt(String content, String key) {
        try {
            //获得密码的字节数组
            byte[] raw = key.getBytes();
            //根据密码生成AES密钥
            SecretKeySpec skey = new SecretKeySpec(raw, "AES");
            //根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            //获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_content = content.getBytes("utf-8");
            //密码器加密数据
            byte[] encode_content = cipher.doFinal(byte_content);
            //将加密后的数据转换为字符串返回
            return Base64.encodeBase64String(encode_content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param encryptStr
     * @param decryptKey
     * @return
     */
    public static String decrypt(String encryptStr, String decryptKey) {
        try {
            //获得密码的字节数组
            byte[] raw = decryptKey.getBytes();
            //根据密码生成AES密钥
            SecretKeySpec skey = new SecretKeySpec(raw, "AES");
            //根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            cipher.init(Cipher.DECRYPT_MODE, skey);
            //把密文字符串转回密文字节数组
            byte[] encode_content = Base64.decodeBase64(encryptStr);
            //密码器解密数据
            byte[] byte_content = cipher.doFinal(encode_content);
            //将解密后的数据转换为字符串返回
            return new String(byte_content, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // 加密的key,前后端需要保持一致
        String key = "12345ABCDErwerw2";
        Map<String, String> map = Maps.newHashMap();
        map.put("dmUserMobile","177****2234");
        map.put("dmOrgId","13233");
        map.put("dmUserId","1234112");
        map.put("dmCompanyName","猎聘北京有限公司");
        String s2 = JSON.toJSONString(map);
        System.out.println(s2);
        // 加密
        String s = encrypt(s2, key);
        System.out.println(s);
        // 解密
        String s1 = decrypt(s, key);
        System.out.println(s1);
    }
}
