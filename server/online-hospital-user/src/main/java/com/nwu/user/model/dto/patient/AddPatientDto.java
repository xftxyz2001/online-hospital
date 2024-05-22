package com.nwu.user.model.dto.patient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/23 21:38
 **/
@Data
@ApiModel(value = "添加就诊人Dto")
public class AddPatientDto {
    @ApiModelProperty(value = "就诊人姓名")
    String name;
    @ApiModelProperty(value = "证件类型")
    String certificatesType;
    @ApiModelProperty(value = "证件号")
    String certificatesNo;
    @ApiModelProperty(value = "性别，0为女，1为男")
    Integer sex;
    @ApiModelProperty(value = "出生年月")
    private LocalDate birthdate;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "是否结婚 0.否 1.是")
    private Integer isMarry;

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
