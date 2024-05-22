package com.nwu.inquiry.model.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/2 16:26
 **/
@Data
@ApiModel(value = "添加问诊Dto")
public class AddInquiryApplicationDto {

    @ApiModelProperty(value = "就诊人id")
    private Long patientId;

    @ApiModelProperty(value = "病情描述信息（300字以内）")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")
    private Long outpatientId;

    @ApiModelProperty(value = "医生id")
    private Long doctorId;
}
