package com.nwu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/22 18:39
 **/
@Component
@Data
@ConfigurationProperties(prefix = "nwu.wechat")
public class WeChatProperties {
    private String appid;
    private String secret;

}
