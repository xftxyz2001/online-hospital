package com.nwu.user.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwu.user.client.WeChatTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(WeChatProperties.class)
public class WeChatAutoConfiguration {

    @Bean
    public WeChatTemplate weChatTemplate(WeChatProperties weChatProperties, RestTemplate restTemplate,
                                         ObjectMapper objectMapper) {
        return new WeChatTemplate(weChatProperties, restTemplate, objectMapper);
    }
}
