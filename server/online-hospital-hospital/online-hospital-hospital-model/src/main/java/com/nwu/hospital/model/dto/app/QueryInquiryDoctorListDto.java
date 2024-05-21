package com.nwu.hospital.model.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/4/1 22:35
 **/
@Data
@ToString
@ApiModel(value = "查询问诊医生列表Dto")
public class QueryInquiryDoctorListDto {
    @ApiModelProperty(value = "医院id")
    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")
    private Long outpatientId;
}
