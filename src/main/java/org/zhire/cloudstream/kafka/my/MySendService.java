package org.zhire.cloudstream.kafka.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

// 绑定自定义通道
@EnableBinding(MySource.class)
public class MySendService {

    @Autowired
    private MySource source;

    public void sendMsg(String msg) {
        source.myOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
