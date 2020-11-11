package org.zhire.config.mq;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhire.rocket.MyRocketDelayMQListener;
import org.zhire.rocket.MyRocketMQTestListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 消费者配置
 */
@Slf4j
@Configuration
public class MqConsumerConfig {

    @Autowired
    private RocketMQConfig rocketMQConfig;

    @Autowired
    private MyDataConfig myDataConfig;

    @Autowired
    private MyRocketMQTestListener myRocketMQTestListener;

    @Autowired
    private MyRocketDelayMQListener myRocketDelayMQListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean buildConsumer() {
        ConsumerBean consumerBean = new ConsumerBean();
        //配置文件
        Properties properties = rocketMQConfig.getMqPropertie();
        properties.setProperty(PropertyKeyConst.GROUP_ID, myDataConfig.getGroupId());
        //将消费者线程数固定为20个 20为默认值
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "20");
        consumerBean.setProperties(properties);

        //订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<Subscription, MessageListener>();
        //topic
        Subscription subscription = new Subscription();
        subscription.setTopic(myDataConfig.getTopicName());
        subscription.setExpression("*");
        subscriptionTable.put(subscription, myRocketMQTestListener);

        //topic
        Subscription subscription2 = new Subscription();
        subscription2.setTopic(myDataConfig.getPayDelayName());
        subscription2.setExpression("*");
        subscriptionTable.put(subscription2, myRocketDelayMQListener);

        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }
}
