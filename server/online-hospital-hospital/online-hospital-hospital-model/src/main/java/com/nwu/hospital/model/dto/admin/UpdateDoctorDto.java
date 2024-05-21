package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "更新医生Dto")
@Data
public class UpdateDoctorDto {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "性别：1.男 0.女")
    private String gender;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "照片")
    private String picture;

    @ApiModelProperty(value = "在线问诊:0.否 1.是")

    private String isInquiry;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String username;


}
