package com.nwu.hospital.model.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 0:27
 **/
@Data
@Schema(description = "医生登录Dto")
public class DoctorLoginDto {
    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;
}
