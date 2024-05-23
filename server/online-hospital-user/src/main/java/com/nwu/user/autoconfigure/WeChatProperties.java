package com.nwu.user.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/22 18:39
 **/
@Data
@ConfigurationProperties(prefix = "nwu.wechat")
public class WeChatProperties {
    private String appid;
    private String secret;

}
