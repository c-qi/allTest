package org.zhire.cloudstream.springkafka;

import cn.hutool.core.collection.CollUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Date 2020/12/26 15:36
 * @Author by chenqi
 */
@Component
public class Producer {

    public static final String TOPIC = "spring-kafka-demo";
    public static final String TOPIC2 = "spring-kafka-demo02";

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    /**
     * 同步发消息
     *
     * @param age
     * @return
     */
    public SendResult syncSend(Integer age) {
        // 创建 Demo01Message 消息
        HashMap<Object, Object> map = CollUtil.newHashMap();
        map.put("name", "cq");
        map.put("age", age);
        // 同步发送消息
        try {
            return kafkaTemplate.send(TOPIC, map).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步发消息
     *
     * @param age
     * @return
     */
    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer age) {
        // 创建 Demo01Message 消息
        HashMap<Object, Object> map = CollUtil.newHashMap();
        map.put("name", "cq");
        map.put("age", age);
        // 异步发送消息
        return kafkaTemplate.send(TOPIC, map);
    }

    /**
     * 同步发消息-到指定分区
     *
     * @param age
     * @return
     */
    public SendResult syncSend2(Integer age) {
        // 创建 Demo01Message 消息
        HashMap<Object, Object> map = CollUtil.newHashMap();
        map.put("name", "cq");
        map.put("age", age);
        // 同步发送消息
        try {
            return kafkaTemplate.send(TOPIC, String.valueOf(age), map).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 手动提交
     *
     * @param age
     * @return
     */
    public SendResult syncSend3(Integer age) {
        // 创建 Demo01Message 消息
        HashMap<Object, Object> map = CollUtil.newHashMap();
        map.put("name", "cq");
        map.put("age", age);
        // 同步发送消息
        try {
            return kafkaTemplate.send(TOPIC2, map).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
