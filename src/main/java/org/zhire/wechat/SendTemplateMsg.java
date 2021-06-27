package org.zhire.wechat;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/10/16 17:53
 * @Author by chenqi
 */
public class SendTemplateMsg {

    @Test
    public void test() {
         sendTemplate();
    }

    /**
     * 获取token
     *
     * @return
     */
    public String getToken() {
        Map<String, Object> map = new HashMap<>(5);
        map.put("grant_type", "client_credential");
        map.put("appid", "wxe7f956678c232546");
        map.put("secret", "7516a5ccce600d7f9f21a88784e9d3b0");
        String postResult = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token", map);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        System.out.println(jsonObject);
        return jsonObject.getString("access_token");
    }

    // String appid, Long userId, String bussinessCode, List<String> keyWords
    public void sendTemplate() {
//        String token = getToken();
        String token = "45_04IH8bAMTHfLee0XcIoEcf4Vm3acn3FFzrBeBuoaTVOmDjL3hEFGD9Cfa_kJg542OS7QQdL-oVPmD9hn3GuE3DOMo8c9Gq2IxTU54zNWmlhJVcxdcMu4EsWDw1iZifqM9w4JKZMiX5K_LEKIBLVdAJAVFP";
        String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
        JSONObject jsonObject = new JSONObject();
        // openId
        jsonObject.put("touser", "owEcX65sUmmMfIhOIQ5yhxnzbaBw");
        jsonObject.put("template_id", "xbowNsoG9GP9-cyfIEMXQ13ebyVByx3sms_e-Fro5LQ");
        jsonObject.put("url", "http://www.qq.com");

        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "chenqi");
        first.put("color", "#173177");
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", "1888");
        keyword1.put("color", "#173177");
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "girl");
        keyword2.put("color", "#173177");
        JSONObject remark = new JSONObject();
        remark.put("value", "hello");
        remark.put("color", "#173177");

//        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
//        data.put("remark", remark);

        jsonObject.put("data", data);

        String string = HttpUtil.post(postUrl, jsonObject.toJSONString());


        JSONObject result = JSON.parseObject(string);
        System.out.println(result);

    }
}

