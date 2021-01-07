package org.zhire.cloudstream.springkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

/**
 * @Date 2020/12/26 15:46
 * @Author by chenqi
 */
@Slf4j
//@Component
public class Consumer01 {

    @KafkaListener(topics = Producer.TOPIC, groupId = "kafka-consumer-01-group-" + Producer.TOPIC)
    public void onMessage(Map<Object, Object> message) {
        log.info("消费者1，线程编号:{} 消息内容：{}", Thread.currentThread().getId(), message);
    }

}
