package org.zhire.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Date 2021/2/23 14:21
 * @Author by chenqi
 */
@Slf4j
public class RsaUtils {
    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static PublicKey string2PublicKey(String pubStr) throws Exception {
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static PrivateKey string2PrivateKey(String priStr) throws Exception {
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //公钥加密
    public static String publicEncrypt(String content, String publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, string2PublicKey(publicKey));
            byte[] bytes = cipher.doFinal(content.getBytes());
            return byte2Base64(bytes);
        } catch (Exception e) {
            log.error("公钥加密失败{}", e);
        }
        return null;
    }

    //私钥解密
    public static String privateDecrypt(String content, String privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, string2PrivateKey(privateKey));
            byte[] bytes = cipher.doFinal(base642Byte(content));
            return new String(bytes);
        } catch (Exception e) {
            log.error("私钥解密失败{}", e);
        }
        return null;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    /**
     * 私钥匙加密或解密
     *
     * @param content
     * @param privateKeyStr
     * @return
     */
    public static String encryptByprivateKey(String content, String privateKeyStr, int opmode) {
        // 私钥要用PKCS8进行处理
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
        KeyFactory keyFactory;
        PrivateKey privateKey;
        Cipher cipher;
        String text = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 还原Key对象
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(opmode, privateKey);
            //加密解密操作
            text = encryptTxt(opmode, cipher, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String encryptTxt(int opmode, Cipher cipher, String content) {
        byte[] result;
        String text = null;
        try {
            if (opmode == Cipher.ENCRYPT_MODE) { // 加密
                result = cipher.doFinal(content.getBytes());
                text = Base64.encodeBase64String(result);
            } else if (opmode == Cipher.DECRYPT_MODE) { // 解密
                result = cipher.doFinal(Base64.decodeBase64(content));
                text = new String(result, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 公钥匙加密或解密
     *
     * @param content
     * @return
     */
    public static String encryptByPublicKey(String content, String publicKeyStr, int opmode) {
        // 公钥要用X509进行处理
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory;
        PublicKey publicKey;
        Cipher cipher;
        String text = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            // 还原Key对象
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(opmode, publicKey);
            //加密解密操作
            text = encryptTxt(opmode, cipher, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


    // 1.服务端生成公钥和私钥，私钥发给前端，公钥服务端自己保存
    // 2.服务端使用公钥加密
    // 3.前端使用私钥解密
    public static void main(String[] args) {
        try {
            KeyPair keyPair = getKeyPair();
            PrivateKey pairPrivate = keyPair.getPrivate();
            PublicKey pairPublic = keyPair.getPublic();
            // 公钥
            String publicKey = Base64.encodeBase64String(pairPublic.getEncoded());
            System.out.println("公钥：" + publicKey);
            // 私钥
            String privateKey = Base64.encodeBase64String(pairPrivate.getEncoded());
            System.out.println("私钥：" + privateKey);
            // 使用公钥加密
            String content = "张三";
            String txtByPublic = encryptByPublicKey(content, publicKey, Cipher.ENCRYPT_MODE);
            System.out.println("公钥加密结果：" + txtByPublic);
            String txtByPrivate = encryptByprivateKey(txtByPublic, privateKey, Cipher.DECRYPT_MODE);
            System.out.println("私钥解密结果：" + txtByPrivate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
