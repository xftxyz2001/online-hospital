package com.nwu.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 用户登录Dto
 * @DateTime 2024/3/22 19:00
 **/
@Data
@Schema(description = "用户登录Dto")
public class UserLoginDto {
    @Schema(description = "小程序登录code")
    private String code;
}
