package com.nwu.registration.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/20 17:00
 **/
@Data
@ToString
public class QueryDoctorScheduleDto {
    @ApiModelProperty(value = "医院id")

    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")

    private Long outpatientId;


    @ApiModelProperty(value = "排班日期")

    private String workDate;

    @ApiModelProperty(value = "医生id")

    private Long doctorId;

    @ApiModelProperty(value = "排班时间（0.上午1.下午）")

    private Integer workHalf;
}
