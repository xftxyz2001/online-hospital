package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @Description 新增排班Dto
 * @DateTime 2024/3/9 18:31
 **/
@Data
@Schema(description = "新增排班Dto")
public class InsertScheduleDto {
    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    private Long outpatientId;

    @Schema(description = "医生id")
    private Long doctorId;

    @Schema(description = "排班日期")
    private LocalDate workDate;

    @Schema(description = "排班时间（0.上午1.下午）")
    private Integer workHalf;

    @Schema(description = "排班具体时间：1-7 如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7 如果是下午，则是从1点到4点半")
    private Integer workTime;

    @Schema(description = "可预约数")
    private Integer reservedNumber;

    @Schema(description = "挂号费")
    private BigDecimal amount;

    @Schema(description = "排班状态（-1.停诊 0.停约 1.可约）")
    private Integer status;
}
