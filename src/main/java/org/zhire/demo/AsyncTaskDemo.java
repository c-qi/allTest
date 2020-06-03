package org.zhire.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author: chenqi
 * @Date: 2020.6.3 16:36
 */
@Component
public class AsyncTaskDemo {

    @Async
    public Future<String> async01() throws Exception {
        Thread.sleep(9000);
        int i = 1/0;
        System.out.println("执行了方法一");
        return new AsyncResult<String>("OK");
    }

    @Async
    public void async02()throws Exception {
        Thread.sleep(6000);
        int i = 1/0;
        System.out.println("执行了方法二");
    }

    @Async
    public void async03()throws Exception {
        Thread.sleep(3000);
        System.out.println("执行了方法三");
    }


}
