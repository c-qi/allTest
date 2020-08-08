package org.zhire.cloudstream.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zhire.cloudstream.StreamPut;

@Component
//@EnableBinding(value = Receive.class)
@EnableBinding(value = StreamPut.class)
public class RecevieService {

    @Autowired
    private StreamPut streamPut;

    @StreamListener(StreamPut.output)
    // @SendTo(StreamPut.input)
    public void onReceiver(byte[] msg) {
        System.out.println("output:" + new String(msg));
        streamPut.input().send(MessageBuilder.withPayload("okokokok".getBytes()).build());
        //return "okokokok";
    }

    @StreamListener(StreamPut.input)
    public void input(byte[] msg) {
        System.out.println("input:" + new String(msg));
    }
}
