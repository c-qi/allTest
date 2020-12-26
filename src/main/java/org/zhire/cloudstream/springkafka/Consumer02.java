package org.zhire.cloudstream.springkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/12/26 15:46
 * @Author by chenqi
 */
@Slf4j
@Component
public class Consumer02 {

    @KafkaListener(topics = Producer.TOPIC, groupId = "kafka-consumer-02-group-" + Producer.TOPIC)
    public void onMessage(ConsumerRecord<String, String> record) {
        log.info("消费者2，线程编号:{} 消息内容：{}", Thread.currentThread().getId(), record.value());
    }

}
