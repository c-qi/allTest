package org.zhire.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhire.annotation.User;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.utils.AuthToken;
import org.zhire.utils.DESUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * token测试
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private AuthToken authToken;

    private static String TOKEN_DES_KEY = "tokenkey12345678";


    /**
     * 登录成功返回给前端token
     * 之后每次请求过来从header带上token
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        String mame = "cq";
        Long id = 8888L;
        return createToken(mame, id);
    }


    @RequestMapping("/get")
    public String get(@User IOCUser user) {
        return user.toString();
    }


    public String createToken(String userName, Long id) {
        String token = "";
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userId", id);
            jsonObj.put("userName", userName);
            String encrypt = Base64.encodeBase64String(DESUtils.encrypt(jsonObj.toJSONString().getBytes(), Base64.decodeBase64(TOKEN_DES_KEY)));
            Map<String, String> userInfo = new HashMap<>(2);
            userInfo.put("token", encrypt);
            token = authToken.createToken(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

}
