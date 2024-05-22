package com.nwu.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/7 16:20
 **/
@ToString
@Data
@ApiModel(value = "修改就诊人信息参数模型")
public class UpdatePatientInfoDto {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别 0.女1.男")
    private Integer sex;

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

    @ApiModelProperty(value = "联系人姓名")
    private String contactsName;

    @ApiModelProperty(value = "联系人手机")
    private String contactsPhone;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
