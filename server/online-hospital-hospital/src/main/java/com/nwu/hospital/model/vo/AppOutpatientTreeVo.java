package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @Author lcy
 * @Description 小程序门诊树状信息
 * @DateTime 2024/3/17 16:12
 **/
@ToString
@Data
@ApiModel(value = "小程序门诊树状信息")
public class AppOutpatientTreeVo {
    @ApiModelProperty(value = "门诊id")
    private Long id;
    @ApiModelProperty(value = "门诊名称")
    private String text;
}
