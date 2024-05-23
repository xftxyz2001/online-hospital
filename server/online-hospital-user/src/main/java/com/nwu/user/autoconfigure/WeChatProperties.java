package com.nwu.user.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "nwu.wechat")
public class WeChatProperties {
    private String appid;
    private String secret;

}
