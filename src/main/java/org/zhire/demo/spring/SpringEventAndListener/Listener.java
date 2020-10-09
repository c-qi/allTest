package org.zhire.demo.spring.SpringEventAndListener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听服务
 */
@Component
public class Listener implements ApplicationListener<Event> {
    @Override
    public void onApplicationEvent(Event event) {
        Task task = event.getTask();
        System.out.println("监听到消息：" + task);
    }
}
