package com.nwu.hospital;

import com.nwu.base.config.JacksonConfig;
import com.nwu.base.config.OpenAPIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JacksonConfig.class, OpenAPIConfig.class})
public class OnlineHospitalHospitalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHospitalHospitalApiApplication.class, args);
    }

}
