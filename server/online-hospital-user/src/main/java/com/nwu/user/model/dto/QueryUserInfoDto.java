package com.nwu.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 分页请求用户的查询参数
 * @DateTime 2024/3/5 16:55
 **/
@Schema(description = "请求用户查询参数")
@Data
public class QueryUserInfoDto {
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "账号状态")
    private String status;

}
