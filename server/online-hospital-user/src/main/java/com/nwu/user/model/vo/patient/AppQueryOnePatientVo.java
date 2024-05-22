package com.nwu.user.model.vo.patient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/24 9:07
 **/
@Data
@Builder
public class AppQueryOnePatientVo {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别 0.女1.男")
    private Integer sex;
    @ApiModelProperty(value = "证件类型")
    private String certificatesType;

    @ApiModelProperty(value = "证件编号")
    private String certificatesNo;

    @ApiModelProperty(value = "就诊卡号")
    private String cardNo;
    @ApiModelProperty(value = "出生年月")
    private LocalDate birthdate;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "详情地址")
    private String addressDetail;

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
