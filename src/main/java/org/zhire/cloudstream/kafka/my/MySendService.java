package org.zhire.cloudstream.kafka.my;

import org.springframework.beans.factory.annotation.Autowired;

//绑定自定义通道
//@EnableBinding(MySource.class)
public class MySendService {

    @Autowired
    private MySource source;

    public void sendMsg(String msg) {
//        int i = new Random().nextInt();
//        source.myOutput()
//                .send(MessageBuilder.withPayload(user)
//                        .setHeader("type", user.getClass().getSimpleName())
//                        .build());
//            source.myOutput().send(MessageBuilder.withPayload(user).build());
    }


}
