package org.zhire.demo.spring.SpringEventAndListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private ApplicationContext application;

    @RequestMapping("/get")
    public String get() {
        Task task = new Task();
        task.setId(1);
        task.setName("cq");
        // 发布监听事件
        application.publishEvent(new Event(this, task));
        return "ok";

    }
}


