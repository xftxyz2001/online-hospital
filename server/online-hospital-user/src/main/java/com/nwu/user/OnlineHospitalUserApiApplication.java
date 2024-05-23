package com.nwu.user;

import com.nwu.base.config.JacksonConfig;
import com.nwu.base.config.MybatisPlusConfig;
import com.nwu.base.config.OpenAPIConfig;
import com.nwu.base.config.WebMvcConfig;
import com.nwu.base.jwt.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Import({JacksonConfig.class, OpenAPIConfig.class, JwtInterceptor.class, WebMvcConfig.class, MybatisPlusConfig.class,
        RestTemplate.class})
public class OnlineHospitalUserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalUserApiApplication.class, args);
    }

}
