package org.zhire.cloudstream.output;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Send {
   // @Output("output")
    SubscribableChannel send();
}
