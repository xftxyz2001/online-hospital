package com.nwu.system.autoconfigure;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliOSSProperties.class)
public class AliOSSAutoConfiguration {

    @Bean
    public OSSClientBuilder ossClientBuilder() {
        return new OSSClientBuilder();
    }

    @Bean
    @ConditionalOnBean(OSSClientBuilder.class)
    public OSS ossClient(OSSClientBuilder ossClientBuilder, AliOSSProperties aliOSSProperties) {
        return ossClientBuilder.build(aliOSSProperties.getEndpoint(), aliOSSProperties.getAccessKeyId(), aliOSSProperties.getAccessKeySecret());
    }
}
