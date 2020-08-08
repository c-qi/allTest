package org.zhire.cloudstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface StreamPut {
    String input = "input";
    String output = "output";

    @Input(StreamPut.input)
    SubscribableChannel input();

    @Output(StreamPut.output)
    SubscribableChannel output();
}
