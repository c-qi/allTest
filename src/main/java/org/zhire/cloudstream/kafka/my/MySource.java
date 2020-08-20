package org.zhire.cloudstream.kafka.my;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    String str = "myOutput";   // 管道名称为"myOutput"

    @Output(str)
    MessageChannel myOutput();
}
