package com.nwu.user.model.dto.doctoraccount;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "医生登录Dto")
public class DoctorAccountLoginDto {
    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;
}
