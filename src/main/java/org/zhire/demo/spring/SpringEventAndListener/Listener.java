package org.zhire.demo.spring.SpringEventAndListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步监听服务，不然默认是同步监听后处理
 */
@Component
@Slf4j
@Async
public class Listener implements ApplicationListener<Event> {
    @Override
    public void onApplicationEvent(Event event) {
        Task task = event.getTask();
        System.out.println("监听到消息：" + task);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
