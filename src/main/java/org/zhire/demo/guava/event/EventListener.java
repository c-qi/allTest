package org.zhire.demo.guava.event;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/12/28 11:17
 * @Author by chenqi
 */
@Async
@Slf4j
@Component
public class EventListener {

    @Subscribe
    public void listen(TestEvent event) throws Exception {
        log.info("thread:{},content:{}", Thread.currentThread().getId(), JSON.toJSONString(event));
        Thread.sleep(3000);
    }


}
