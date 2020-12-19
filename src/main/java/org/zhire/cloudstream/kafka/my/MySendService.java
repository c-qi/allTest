package org.zhire.cloudstream.kafka.my;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.zhire.pojo.User;

import java.util.Random;

import static java.lang.Long.valueOf;

//绑定自定义通道
@EnableBinding(MySource.class)
public class MySendService {

    @Autowired
    private MySource source;

    public void sendMsg(String msg) {
        int i = new Random().nextInt();
        for (int j = 0; j < 3; j++) {
            User user = new User();
            user.setId(valueOf(i));
            user.setName(msg);
            //source.myOutput().send(MessageBuilder.withPayload(msg).setHeader("flag","cq").build());
            source.myOutput().send(MessageBuilder.withPayload(user).build());
        }

    }
}
