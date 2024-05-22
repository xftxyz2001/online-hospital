package com.nwu;

import com.nwu.base.config.JacksonConfig;
import com.nwu.base.jwt.JwtInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JacksonConfig.class, JwtInterceptor.class})
public class OnlineHospitalUserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalUserApiApplication.class, args);
    }

}
