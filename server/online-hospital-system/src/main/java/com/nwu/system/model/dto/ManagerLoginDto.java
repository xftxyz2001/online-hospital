package com.nwu.system.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @version 1.0
 * @Author lcy
 * @Description 管理员登录Dto
 * @DateTime 2024/3/13 23:11
 **/
@ApiModel(value = "管理员登录Dto")
@Data
public class ManagerLoginDto {

    @ApiModelProperty(value = "用户名")
    String username;
    @ApiModelProperty(value = "密码")
    String password;

}
