package org.zhire.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhire.config.FeignConfig;

import java.util.Optional;
//      String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
@FeignClient(name = "SubscribeMessageService", url = "https://api.weixin.qq.com", configuration = FeignConfig.class)
public interface SubscribeMessageService {

    /**小程序发送消息*/
    @PostMapping(value = "/cgi-bin/message/template/send")
    Optional<WxResponse> subscribeSend(
            @RequestParam(name = "access_token") String access_token,
            @RequestBody SubscribeBody subscribeBody);
}
