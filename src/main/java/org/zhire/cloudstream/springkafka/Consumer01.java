package org.zhire.cloudstream.springkafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;

import java.util.Iterator;

/**
 * @Date 2020/12/26 15:46
 * @Author by chenqi
 */
@Slf4j
//@Component
public class Consumer01 {

//    @KafkaListener(topics = Producer.TOPIC, groupId = "kafka-consumer-01-group-" + Producer.TOPIC)
//    public void onMessage(Map<Object, Object> message) {
//        log.info("消费者1，线程编号:{} 消息内容：{}", Thread.currentThread().getId(), message);
//    }

    /**
     * 同步书画院作品信息
     */
    @KafkaListener(topics = "stream-demo", groupId = "group-3")
    public void userPaintingTopic(
                                  ConsumerRecord<String, Object> record,
                                  Acknowledgment ack,
                                  @org.springframework.messaging.handler.annotation.Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            log.info("topic:{}", topic);
            String type = getType(record.headers());
            log.info("type:{}", type);
            type = type.replaceAll("\"", "");
            log.info("同步信息type:{},value:{}", type, record.value());
            if ("WorksMessage".equals(type)) {
                Object message = JSONObject.parseObject(record.value().toString(), Object.class);
                log.info("mess:{}", JSON.toJSONString(message));
            } else
                log.error("同步type:{},payload:{}", type, record.value());
        } catch (Exception e) {
            log.error("同步异常", e);
        } finally {
            ack.acknowledge();
        }
    }

    /**
     * 获取type
     *
     * @param headers
     * @return
     */
    public String getType(Headers headers) {
        String value = "";
        Iterator<Header> iterator = headers.iterator();
        while (iterator.hasNext()) {
            org.apache.kafka.common.header.Header next = iterator.next();
            if (StringUtils.isNotBlank(next.key()) && "type".equals(next.key())) {
                value = new String(next.value());
                break;
            }
        }
        return value;
    }

}
