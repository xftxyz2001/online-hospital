package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description 小程序专科树状信息
 * @DateTime 2024/3/17 16:13
 **/
@ToString
@Data
@ApiModel(value = "小程序专科树状信息")
public class AppSpecialistTreeVo {
    @ApiModelProperty(value = "专科名称")
    private String text;
    @ApiModelProperty(value = "包含的门诊")
    private List<AppOutpatientTreeVo> children;

}