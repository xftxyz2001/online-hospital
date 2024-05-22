package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("添加专科Dto")
@Data
public class AddSpecialistDto {
    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    @ApiModelProperty(value = "专科名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String disc;
}
