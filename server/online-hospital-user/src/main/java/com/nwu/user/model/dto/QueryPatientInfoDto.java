package com.nwu.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/7 17:09
 **/
@ApiModel(value = "请求就诊人查询参数")
@Data
public class QueryPatientInfoDto {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "性别")
    private String sex;

}
