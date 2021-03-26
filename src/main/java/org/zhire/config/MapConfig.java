package org.zhire.config;

/**
 * @Date 2021/3/26 11:35
 * @Author by chenqi
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

// xgx:
// skuMap:
//  s1: 1234
//  s2: 91250
//  s3: 2365
@Component
@Data
@ConfigurationProperties(prefix = "xgx")
public class MapConfig {

    private Map<String, Integer> skuMap;


}
