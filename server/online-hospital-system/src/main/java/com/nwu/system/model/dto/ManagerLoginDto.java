package com.nwu.system.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理员登录Dto")
@Data
public class ManagerLoginDto {

    @Schema(description = "用户名")
    String username;
    @Schema(description = "密码")
    String password;

}
