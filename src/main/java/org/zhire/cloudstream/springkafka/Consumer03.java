package org.zhire.cloudstream.springkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Date 2020/12/26 15:46
 * @Author by chenqi
 */
@Slf4j
//@Component
public class Consumer03 {

    @KafkaListener(topics = Producer.TOPIC2, groupId = "kafka-consumer-03-group-" + Producer.TOPIC)
    public void onMessage(Map<Object, Object> message, Acknowledgment acknowledgment) {
        log.info("消费者3，线程编号:{} 消息内容：{}", Thread.currentThread().getId(), message);
        // 提交消费进度
        acknowledgment.acknowledge();

    }

}
