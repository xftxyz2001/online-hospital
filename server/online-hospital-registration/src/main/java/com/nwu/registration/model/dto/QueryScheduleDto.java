package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/9 1:31
 **/
@Data
public class QueryScheduleDto {
    @Schema(description = "医院id")

    private Long hospitalId;

    @Schema(description = "门诊id")

    private Long outpatientId;


    @Schema(description = "排班日期")

    private String workDate;
}
