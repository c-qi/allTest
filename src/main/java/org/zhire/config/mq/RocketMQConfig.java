package org.zhire.config.mq;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Data;

import java.util.Properties;

/**
 * 连接阿里云配置
 *
 * @author chenqi
 */
@Data
//@Configuration
//@ConfigurationProperties(prefix = "aliyun-ons")
public class RocketMQConfig {

    private String regionId = "cn-shanghai";

    private String accessKey = "";

    private String secretKey = "";

    private String instanceId = "";

    private String nameServerAddr = "";

    public Properties getMqPropertie() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, this.secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.nameServerAddr);
        return properties;
    }
}