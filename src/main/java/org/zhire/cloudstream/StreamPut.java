package org.zhire.cloudstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface StreamPut {
    String input = "input1";
    String output = "output1";

    @Input(StreamPut.input)
    SubscribableChannel input();

    @Output(StreamPut.output)
    SubscribableChannel output();
}
