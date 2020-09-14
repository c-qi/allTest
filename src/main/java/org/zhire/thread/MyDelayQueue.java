package org.zhire.thread;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 */
@Data
@Slf4j
public class MyDelayQueue implements Delayed, Runnable {
    /**
     * 延迟时间
     */
    private long delayMinute;
    /**
     * 时分秒
     **/
    private TimeUnit unit;

    /**
     * 转换为毫秒后的延时时间
     */
    private long delayTimes;

    /**
     * 延迟队列请求参数
     */
    private Map param;

    public static final DelayQueue<MyDelayQueue> DELAY_QUEUE = new DelayQueue();

    public MyDelayQueue(long delayMinute, TimeUnit unit, Map param) {
        this.delayMinute = delayMinute;
        this.unit = unit;
        this.delayTimes = System.currentTimeMillis() + (delayMinute <= 0 ? 0 : unit.toMillis(delayMinute));
        this.param = param;
    }

    public MyDelayQueue() {
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public void run() {
        try {
            MyDelayQueue task;
            while ((task = DELAY_QUEUE.take()) != null) {
                log.info("延时队列，收到任务，任务内容：[{}]", JSON.toJSONString(task));
            }
        } catch (Exception e) {
            log.error("获取延迟任务异常", e);
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayTimes - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        Map map = new HashMap<>();
        map.put("name", "cq");
        map.put("age", 11);
        // DelayQueue<MyDelayQueue> queue = new DelayQueue();
        // queue.add(new MyDelayQueue(1, TimeUnit.SECONDS, map));
        // MyDelayQueue take = queue.take();
        // System.out.println(take);

        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(1, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(2, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(3, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(4, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(5, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(6, TimeUnit.SECONDS, map));

        MyDelayQueue myDelayQueue = new MyDelayQueue();
        Thread thread = new Thread(myDelayQueue);
        thread.start();

    }

}
