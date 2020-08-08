package org.zhire.cloudstream.input;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Receive {
    //@Input("input")
    SubscribableChannel receiver();

}
