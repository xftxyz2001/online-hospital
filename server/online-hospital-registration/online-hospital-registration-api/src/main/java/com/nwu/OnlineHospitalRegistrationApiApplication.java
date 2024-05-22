package com.nwu;

import com.nwu.base.json.JacksonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JacksonConfig.class})
@EnableFeignClients
public class OnlineHospitalRegistrationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalRegistrationApiApplication.class, args);
    }
}
