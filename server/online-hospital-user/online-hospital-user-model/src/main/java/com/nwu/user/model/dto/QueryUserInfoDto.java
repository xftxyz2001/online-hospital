package com.nwu.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @Author lcy
 * @Description 分页请求用户的查询参数
 * @DateTime 2024/3/5 16:55
 **/
@ApiModel(value = "请求用户查询参数")
@Data
@ToString
public class QueryUserInfoDto {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "账号状态")
    private String status;

}
