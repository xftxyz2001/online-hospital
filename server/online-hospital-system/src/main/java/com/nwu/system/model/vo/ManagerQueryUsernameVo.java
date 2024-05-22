package com.nwu.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理员查询名称vo")
@Data
public class ManagerQueryUsernameVo {

    @Schema(description = "用户名")
    private String username;
}
