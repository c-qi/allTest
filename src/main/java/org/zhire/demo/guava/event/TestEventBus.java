package org.zhire.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2020/12/28 11:17
 * @Author by chenqi
 */
@Controller
@RequestMapping("/event")
public class TestEventBus {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private EventListener eventListener;


    @RequestMapping("/test")
    public void testReceiveEvent() {
        eventBus.register(eventListener);
        eventBus.post(new TestEvent(1, "cq"));
        eventBus.post(new TestEvent(2, "cq2"));
        eventBus.post(new TestEvent(3, "cq3"));
    }
}
