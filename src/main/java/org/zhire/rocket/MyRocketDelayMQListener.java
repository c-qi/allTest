package org.zhire.rocket;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 延时消息消费者
 *
 * @Date 2020/11/11 17:13
 * @Author by chenqi
 */
@Slf4j
//@Component
public class MyRocketDelayMQListener implements MessageListener {
    @Override
    public Action consume(Message message, ConsumeContext context) {
        String tag = message.getTag();
        String key = message.getKey();
        String msgBody = new String(message.getBody());
        log.info("收到消息消息:msgBody:{}, tag:{}, key:{}", msgBody, tag, key);
        return Action.CommitMessage;
    }
}
