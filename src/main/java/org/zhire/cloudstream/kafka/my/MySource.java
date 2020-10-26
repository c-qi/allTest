package org.zhire.cloudstream.kafka.my;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySource {
    String myOutput = "myOutput";   // 管道名称为"myOutput"
    String input = "input";   // 接收管道名称为"input"
    String input2 = "input2";   // 接收管道名称为"input"


    @Output(myOutput)
    MessageChannel myOutput();

    @Input(input)
    SubscribableChannel receive();

    @Input(input2)
    SubscribableChannel receive2();

}
