package com.nwu.registration.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("schedule")
@Schema(description = "Schedule对象-排班信息")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "医院id")
    @TableField("hospital_id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    @TableField("outpatient_id")
    private Long outpatientId;

    @Schema(description = "医生id")
    @TableField("doctor_id")
    private Long doctorId;

    @Schema(description = "排班日期")
    @TableField("work_date")
    private LocalDate workDate;

    @Schema(description = "排班时间（0.上午1.下午）")
    @TableField("work_half")
    private Integer workHalf;

    @Schema(description = "排班具体时间：1-7 如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7 如果是下午，则是从1点到4点半")
    @TableField("work_time")
    private Integer workTime;

    @Schema(description = "可预约数")
    @TableField("reserved_number")
    private Integer reservedNumber;

    @Schema(description = "剩余预约数")
    @TableField("available_number")
    private Integer availableNumber;

    @Schema(description = "挂号费")
    @TableField("amount")
    private BigDecimal amount;

    @Schema(description = "排班状态（-1.停诊 0.停约 1.可约）")
    @TableField("status")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
