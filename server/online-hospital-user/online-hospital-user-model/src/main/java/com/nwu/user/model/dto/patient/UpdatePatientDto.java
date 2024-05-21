package com.nwu.user.model.dto.patient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改就诊人Dto")
public class UpdatePatientDto {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "省")
    private String provinceName;

    @ApiModelProperty(value = "市")
    private String cityName;

    @ApiModelProperty(value = "区")
    private String districtName;

    @ApiModelProperty(value = "地区码")
    private String areaCode;

    @ApiModelProperty(value = "详情地址")
    private String address;
}
