package com.nwu.user.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/23 18:09
 **/
@Data
@Builder
@Schema(description = "请求用户名Vo")
public class QueryUsernameVo {
    @Schema(description = "用户名")
    String username;
}
