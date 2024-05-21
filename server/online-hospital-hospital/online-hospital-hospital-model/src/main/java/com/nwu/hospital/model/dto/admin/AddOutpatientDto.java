package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("添加门诊Dto")
@Data
public class AddOutpatientDto {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    @ApiModelProperty(value = "所属专科")
    private Long specialistId;

    @ApiModelProperty(value = "介绍")
    private String disc;

}
