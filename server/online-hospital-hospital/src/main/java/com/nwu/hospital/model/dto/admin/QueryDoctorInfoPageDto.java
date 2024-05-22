package com.nwu.hospital.model.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "条件分页查询医生列表Dto")
public class QueryDoctorInfoPageDto {
    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "性别：1.男 0.女")
    private Integer gender;

    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    @ApiModelProperty(value = "所属门诊")
    private Long outpatientId;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "是否问诊(0.否 1.是)")
    private Integer isInquiry;

}
