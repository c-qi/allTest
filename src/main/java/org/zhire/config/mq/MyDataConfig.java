package org.zhire.config.mq;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * topic和group的配置
 *
 * @author chenqi
 */
@Data
@Configuration
//@ConfigurationProperties(prefix = "rocket-data")
public class MyDataConfig {

    private String topicName = "my-test-topic-name";

    private String groupId = "GID_my-test-group-name";

    private String tag = "my-test-tag";

    private String delayTag = "my-test-delay-tag";

    private String payDelayName = "my-delay-topic-name";


}