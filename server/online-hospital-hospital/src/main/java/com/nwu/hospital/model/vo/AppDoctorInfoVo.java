package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "App单个医生信息Vo")
@Data
public class AppDoctorInfoVo {
    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "简介")
    private String introduce;
    @ApiModelProperty(value = "照片")
    private String picture;

    @ApiModelProperty(value = "所属医院")
    private String hospitalName;

    @ApiModelProperty(value = "所属门诊")
    private String outpatientName;

    @ApiModelProperty(value = "是否问诊(0.否 1.是)")
    private Integer isInquiry;

}
