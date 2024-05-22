package com.nwu.registration.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/9 1:31
 **/
@Data
public class QueryScheduleDto {
    @ApiModelProperty(value = "医院id")

    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")

    private Long outpatientId;


    @ApiModelProperty(value = "排班日期")

    private String workDate;
}
