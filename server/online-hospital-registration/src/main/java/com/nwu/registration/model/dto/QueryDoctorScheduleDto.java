package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/20 17:00
 **/
@Data
public class QueryDoctorScheduleDto {
    @Schema(description = "医院id")

    private Long hospitalId;

    @Schema(description = "门诊id")

    private Long outpatientId;


    @Schema(description = "排班日期")

    private String workDate;

    @Schema(description = "医生id")

    private Long doctorId;

    @Schema(description = "排班时间（0.上午1.下午）")

    private Integer workHalf;
}
