package com.nwu.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "用户登录Dto")
public class UserLoginDto {
    @Schema(description = "小程序登录code")
    private String code;
}
