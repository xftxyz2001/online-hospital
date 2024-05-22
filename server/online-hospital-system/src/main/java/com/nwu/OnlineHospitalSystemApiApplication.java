package com.nwu;

import com.nwu.base.config.JacksonConfig;
import com.nwu.base.config.OpenAPIConfig;
import com.nwu.base.config.WebMvcConfig;
import com.nwu.base.jwt.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JacksonConfig.class, OpenAPIConfig.class, JwtInterceptor.class, WebMvcConfig.class})
public class OnlineHospitalSystemApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalSystemApiApplication.class, args);
    }

}
