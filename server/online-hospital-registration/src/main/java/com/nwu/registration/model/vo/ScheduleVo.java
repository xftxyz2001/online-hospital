package com.nwu.registration.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Schema(description = "ScheduleVo对象")
public class ScheduleVo {

    private Long id;

    @Schema(description = "医院id")

    private Long hospitalId;

    @Schema(description = "门诊id")

    private Long outpatientId;

    @Schema(description = "医生id")

    private Long doctorId;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "医生名称")
    private String doctorName;

    @Schema(description = "医生介绍")
    private String doctorIntroduce;

    @Schema(description = "照片")
    private String picture;
    @Schema(description = "排班日期")

    private LocalDate workDate;

    @Schema(description = "排班时间（0.上午1.下午）")

    private Integer workHalf;

    @Schema(description = "排班具体时间：1-7 如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7 如果是下午，则是从1点到4点半")

    private Integer workTime;

    @Schema(description = "可预约数")

    private Integer reservedNumber;

    @Schema(description = "剩余预约数")

    private Integer availableNumber;

    @Schema(description = "挂号费")

    private BigDecimal amount;

    @Schema(description = "排班状态（-1.停诊 0.停约 1.可约）")

    private Integer status;

    @Schema(description = "创建时间")

    private LocalDateTime createTime;


}