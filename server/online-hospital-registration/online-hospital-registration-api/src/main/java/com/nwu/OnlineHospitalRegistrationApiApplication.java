package com.nwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class OnlineHospitalRegistrationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalRegistrationApiApplication.class, args);
    }
}
