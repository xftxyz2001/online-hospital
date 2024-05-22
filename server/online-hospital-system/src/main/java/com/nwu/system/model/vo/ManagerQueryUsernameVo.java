package com.nwu.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("管理员查询名称vo")
@Data
public class ManagerQueryUsernameVo {

    @ApiModelProperty(value = "用户名")
    private String username;
}
