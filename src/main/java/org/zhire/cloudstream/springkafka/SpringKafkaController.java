package org.zhire.cloudstream.springkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/12/26 15:59
 * @Author by chenqi
 */
@Slf4j
@RestController
@RequestMapping("/kafkaSpring")
public class SpringKafkaController {

    @Autowired
    private Producer producer;

    @RequestMapping("/t1")
    private String t1() {
        int age = (int) (System.currentTimeMillis() / 1000);
        log.info("同步发送结果：{}", producer.syncSend(age));
        return "OK";
    }

    @RequestMapping("/t2")
    public void t2() {
        int age = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(age).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("发送异常");
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("发送成功");
            }

        });

    }

    @RequestMapping("/t3")
    private String t3() {
        int age = 10;
        log.info("同步发送结果：{}", producer.syncSend2(age));
        return "OK";
    }

    @RequestMapping("/t4")
    private String t4() {
        for (int i = 1; i < 3; i++) {
            log.info("同步发送结果：{}", producer.syncSend3(i));
        }
        return "OK";
    }
}
