package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "添加医生Dto")
@Data
public class AddDoctorDto {
    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "性别：1.男 0.女")
    private Integer gender;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "照片")
    private String picture;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    @ApiModelProperty(value = "所属门诊")
    private Long outpatientId;

    @ApiModelProperty(value = "在线问诊:0.否 1.是")

    private Integer isInquiry;
}
