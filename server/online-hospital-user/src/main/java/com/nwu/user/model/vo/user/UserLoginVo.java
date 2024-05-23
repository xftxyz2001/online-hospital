package com.nwu.user.model.vo.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户登录Vo")
public class UserLoginVo {
    private Long id;
    @Schema(description = "用户唯一表示")
    private String openId;
    @Schema(description = "用户token")
    private String token;
    @Schema(description = "是否为第一次登录 0.否 1.是")
    private Integer isFirst;
}
