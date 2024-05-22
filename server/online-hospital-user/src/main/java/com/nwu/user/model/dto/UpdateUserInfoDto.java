package com.nwu.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/7 13:03
 **/
@Data
@Schema(description = "修改用户信息参数模型")
public class UpdateUserInfoDto {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "昵称")
    private String username;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "头像地址")
    private String avatar;
    @Schema(description = "上次修改时间")
    private LocalDateTime modifiedTime;
}
