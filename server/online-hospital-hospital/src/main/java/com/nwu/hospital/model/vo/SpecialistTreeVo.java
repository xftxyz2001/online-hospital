package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description 专科树状信息
 * @DateTime 2024/3/8 18:26
 **/
@Data
@ApiModel(value = "专科树状信息")
public class SpecialistTreeVo {
    @ApiModelProperty(value = "专科名称")
    private String label;
    @ApiModelProperty(value = "包含的门诊")
    private List<OutpatientTreeVo> children;

}
