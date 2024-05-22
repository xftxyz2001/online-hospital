package com.nwu.user.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "App修改用户信息Dto")
public class AppUpdateUserInfoDto {

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "昵称")
    private String username;

    @Schema(description = "头像地址")
    private String avatar;
}
