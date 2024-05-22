package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 门诊树状信息
 * @DateTime 2024/3/8 18:34
 **/
@Data
@ApiModel(value = "门诊树状信息")
public class OutpatientTreeVo {
    @ApiModelProperty(value = "门诊id")
    private Long value;
    @ApiModelProperty(value = "门诊名称")
    private String label;
}
