package com.nwu;

import com.nwu.base.config.JacksonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JacksonConfig.class})
public class OnlineHospitalGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalGatewayApplication.class, args);
    }
}
