package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@ApiModel("修改专科Dto")
@Data
public class UpdateSpecialistDto {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "专科名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String disc;
}
