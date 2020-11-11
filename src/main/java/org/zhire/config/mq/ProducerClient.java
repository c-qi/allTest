package org.zhire.config.mq;

import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * 生产者配置
 */
//@Configuration
public class ProducerClient {

    @Autowired
    private RocketMQConfig rocketMQConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean buildProducer() {
        ProducerBean producer = new ProducerBean();
        producer.setProperties(rocketMQConfig.getMqPropertie());
        return producer;
    }

}
