package com.nwu.user.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 用户登录Dto
 * @DateTime 2024/3/22 19:00
 **/
@Data
@ApiModel(value = "用户登录Dto")
public class UserLoginDto {
    @ApiModelProperty(value = "小程序登录code")
    private String code;
}
