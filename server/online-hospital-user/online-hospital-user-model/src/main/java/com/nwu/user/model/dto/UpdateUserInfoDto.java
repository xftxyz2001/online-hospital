package com.nwu.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/7 13:03
 **/
@ToString
@Data
@ApiModel(value = "修改用户信息参数模型")
public class UpdateUserInfoDto {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "昵称")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    @ApiModelProperty(value = "上次修改时间")
    private LocalDateTime modifiedTime;
}
