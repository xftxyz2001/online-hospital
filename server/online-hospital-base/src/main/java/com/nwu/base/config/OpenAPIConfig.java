package com.nwu.base.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("网上医院接口文档")
                        .version("2.0")
                        .description(
                                "/user: 用户接口<br>/hospital: 医院接口<br>/registration: 挂号接口<br>/inquiry: 问诊接口<br>/system: 系统接口"));
    }
}
