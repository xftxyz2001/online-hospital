package com.nwu.user.model.dto.doctoraccount;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 0:27
 **/
@Data
@ApiModel(value = "医生登录Dto")
public class DoctorAccountLoginDto {
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String username;
}
