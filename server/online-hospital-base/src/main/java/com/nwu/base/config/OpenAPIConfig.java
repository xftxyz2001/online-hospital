package com.nwu.base.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenAPIConfig {

    String[] contextPaths = {"/user", "/hospital", "/registration", "/inquiry", "/system"};
    String[] descriptions = {"用户接口", "医院接口", "挂号接口", "问诊接口", "系统接口"};

    @Bean
    public OpenAPI customOpenAPI() {

        StringBuilder description = new StringBuilder();
        description.append("**其他接口**<br>");
        for (int i = 0; i < contextPaths.length; i++) {
            description.append("- [" + descriptions[i] + "](" + contextPaths[i] + "/swagger-ui/index.html)<br>");
        }

        return new OpenAPI()
                .info(new Info()
                        .title("网上医院接口文档")
                        .version("0.0.1")
                        .description(description.toString()));
    }
}
