package org.zhire.cloudstream.kafka.defaultkafka;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//消息接受端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的imput关联的。
//@EnableBinding(Sink.class)
public class RecieveService {

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d").build();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(500),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    @StreamListener(Sink.INPUT)
    public void recieve(Object payload) throws Exception{
        executor.execute(()->{
            System.out.println(Thread.currentThread().getName()+ " 收到消息：" + payload);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " 完成：" + payload);
        });
    }

}
