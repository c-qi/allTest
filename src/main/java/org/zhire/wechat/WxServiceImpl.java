package org.zhire.wechat;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhire.feign.SubscribeBody;
import org.zhire.feign.SubscribeMessageService;
import org.zhire.feign.WxResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Date 2020/12/7 18:20
 * @Author by chenqi
 */
@Slf4j
@Service
public class WxServiceImpl implements WxService {

    @Autowired
    private SubscribeMessageService subscribeMessageService;

    @Override
    public void push() {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "chenqi");
        first.put("color", "#173177");
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", "17");
        keyword1.put("color", "#173177");
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "boy");
        keyword2.put("color", "#173177");
        JSONObject remark = new JSONObject();
        remark.put("value", "hello");
        remark.put("color", "#173177");
        data.put("first", first);
        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);
        data.put("remark", remark);
        jsonObject.put("data", data);

        SubscribeBody body = new SubscribeBody();
        body.setTemplate_id("vwAOEs3BdPYhYNo6VV8UWbXJ1ci-H0irFDx8YXYotPE");
        body.setData(jsonObject);
        body.setTouser("owEcX65sUmmMfIhOIQ5yhxnzbaBw");
        log.info("发送微信消息参数：{}", JSON.toJSONString(body));
        Optional<WxResponse> wxResponse = subscribeMessageService.subscribeSend(getToken(), body);
        log.info("发送微信消息结果：{}", wxResponse);
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

}
