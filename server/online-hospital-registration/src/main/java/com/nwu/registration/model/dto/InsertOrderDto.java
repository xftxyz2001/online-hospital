package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Schema(description = "添加订单Dto")
public class InsertOrderDto {

    @Schema(description = "医院编号")
    private Long hospitalId;


    @Schema(description = "门诊编号")
    private Long outpatientId;

    private LocalDate reserveDate;

    private Integer reserveHalf;

    private Integer reserveTime;

    @Schema(description = "预约费")
    private BigDecimal amount;

    @Schema(description = "排班id")
    private Long scheduleId;

    @Schema(description = "医生编号")
    private Long doctorId;

    private Long patientId;
}
