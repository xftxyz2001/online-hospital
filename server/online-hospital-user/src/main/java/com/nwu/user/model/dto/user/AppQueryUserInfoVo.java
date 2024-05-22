package com.nwu.user.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "App查询用户信息Dto")
public class AppQueryUserInfoVo {
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String username;

    @ApiModelProperty(value = "头像地址")
    private String avatar;
}
