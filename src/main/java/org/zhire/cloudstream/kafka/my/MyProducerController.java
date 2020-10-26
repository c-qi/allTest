package org.zhire.cloudstream.kafka.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消费者组（Consumer Group）：同一类 Consumer 的集合，这类 Consumer 通常消费同一类消息且消费逻辑一致。
 * 消费者组使得在消息消费方面，实现负载均衡和容错的目标变得非常容易。
 * 要注意的是，消费者组的消费者实例必须订阅完全相同的 Topic。
 * <p>
 * 对于消费队列的消费者，会有两种消费模式：集群消费（Clustering）和广播消费（Broadcasting）。
 * <p>
 * 集群消费（Clustering）：集群消费模式下,相同 Consumer Group 的每个 Consumer 实例平均分摊消息，同一个分组下的多个实例会只有一个消费者消费，容灾
 * 广播消费（Broadcasting）：广播消费模式下，不相同 Consumer Group 的每个 Consumer 实例都接收全量的消息，
 * 同一个topic下的不同分组下的实例，都会消费，可以解耦，场景如用户注册成功后发同一个topic，不同的分组消费者执行不同的逻辑
 * <p>
 * kafka的并发消费
 * 需要先给topic设置多分区  bin/kafka-topics.sh --zookeeper 127.0.0.1:2181 -alter --partitions 10 --topic stream-demo
 * 然后修改配置 concurrency: 2 # 每个 Consumer 消费线程数的初始大小，默认为 1
 * 假设我们创建10个分区
 * 则两个消费者 即两个线程分别分到5个partition 各自消费
 * <p>
 * 参考：http://www.iocoder.cn/Spring-Cloud/Kafka/
 */
@Controller
@RequestMapping("/kafkamy")
public class MyProducerController {
    @Autowired
    private MySendService sendService;

    @RequestMapping("/send")
    public String send(@RequestParam("msg") String msg) {
        sendService.sendMsg(msg);
        return "ok";
    }

}
