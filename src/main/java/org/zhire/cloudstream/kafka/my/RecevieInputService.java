package org.zhire.cloudstream.kafka.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.messaging.support.ErrorMessage;

@Slf4j
//@Component
//@EnableBinding(value = MySource.class)
public class RecevieInputService {

    // condition 消费过滤
    //@StreamListener(value = MySource.input, condition = "headers['flag'] == 'cq'")
    @StreamListener(value = MySource.input)
    public void onReceiver(byte[] msg) {
        System.out.println("消费者1收到消息:" + Thread.currentThread().getId() + " " + new String(msg));
    }


    @StreamListener(MySource.input2)
    public void onReceiver3(byte[] msg) {
        System.out.println("消费者3收到消息:" + new String(msg));
    }

    /**
     * 局部的异常处理：通过订阅指定错误 Channel
     * 在全局和局部异常处理都定义的情况下，错误消息仅会被符合条件的局部错误异常处理。
     * 如果没有符合条件的，错误消息才会被全局异常处理。
     * 如果异常处理方法成功，没有重新抛出异常，会认定为该消息被消费成功，就不会发到死信队列。
     *
     * @param errorMessage
     */
    @ServiceActivator(inputChannel = "stream-demo.group-1.errors")
    public void handleError(ErrorMessage errorMessage) {
        log.error("[handleError][payload：{}]", errorMessage.getPayload().getMessage());
        log.error("[handleError][originalMessage：{}]", errorMessage.getOriginalMessage());
        log.error("[handleError][headers：{}]", errorMessage.getHeaders());
    }

    /**
     * 全局的异常处理：通过订阅全局错误 Channel
     *
     * @param errorMessage
     */
    @StreamListener(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME) // errorChannel
    public void globalHandleError(ErrorMessage errorMessage) {
        log.error("[globalHandleError][payload：{}]", errorMessage.getPayload().getMessage());
        log.error("[globalHandleError][originalMessage：{}]", errorMessage.getOriginalMessage());
        log.error("[globalHandleError][headers：{}]", errorMessage.getHeaders());
    }

}
