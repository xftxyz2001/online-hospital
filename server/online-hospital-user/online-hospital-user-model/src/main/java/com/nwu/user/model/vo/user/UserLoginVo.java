package com.nwu.user.model.vo.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 用户登录Vo
 * @DateTime 2024/3/22 19:03
 **/
@Data
@ApiModel(value = "用户登录Vo")
public class UserLoginVo {
    private Long id;
    @ApiModelProperty(value = "用户唯一表示")
    private String openId;
    @ApiModelProperty(value = "用户token")
    private String token;
    @ApiModelProperty(value = "是否为第一次登录 0.否 1.是")
    private Integer isFirst;
}
