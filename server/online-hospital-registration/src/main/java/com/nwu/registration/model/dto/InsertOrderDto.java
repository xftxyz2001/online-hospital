package com.nwu.registration.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/24 16:18
 **/
@Data
@ApiModel(value = "添加订单Dto")
public class InsertOrderDto {

    @ApiModelProperty(value = "医院编号")
    private Long hospitalId;


    @ApiModelProperty(value = "门诊编号")
    private Long outpatientId;

    private LocalDate reserveDate;

    private Integer reserveHalf;

    private Integer reserveTime;

    @ApiModelProperty(value = "预约费")
    private BigDecimal amount;

    @ApiModelProperty(value = "排班id")
    private Long scheduleId;

    @ApiModelProperty(value = "医生编号")
    private Long doctorId;

    private Long patientId;
}
