package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("分页条件查询医生列表Vo")
@Data
public class DoctorInfoPageVo {


    @ApiModelProperty(value = "编号")
    private Long id;
    @ApiModelProperty(value = "医生姓名")
    private String name;
    @ApiModelProperty(value = "职称")
    private String professionalTitle;
    @ApiModelProperty(value = "所属医院")
    private String hospitalName;
    @ApiModelProperty(value = "所属门诊")
    private String outpatientName;
    @ApiModelProperty(value = "照片")
    private String picture;


}
