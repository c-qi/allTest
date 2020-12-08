package org.zhire.feign;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class SubscribeBody {

    /**openid*/
    private String touser;

    /**模板id*/
    private String template_id;

    /**点击模板卡片后的跳转页面，仅限本小程序内的页面*/
    private String page = "index";

    /**跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版*/
    private String miniprogram_state;

    /**进入小程序查看”的语言类型*/
    private String lang = "zh_CN";

    /**模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }*/
    private JSONObject data;
}
