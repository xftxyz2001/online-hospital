package com.nwu.registration.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 排班信息
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("schedule")
@ApiModel(value = "Schedule对象", description = "排班信息")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "医院id")
    @TableField("hospital_id")
    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")
    @TableField("outpatient_id")
    private Long outpatientId;

    @ApiModelProperty(value = "医生id")
    @TableField("doctor_id")
    private Long doctorId;

    @ApiModelProperty(value = "排班日期")
    @TableField("work_date")
    private LocalDate workDate;

    @ApiModelProperty(value = "排班时间（0.上午1.下午）")
    @TableField("work_half")
    private Integer workHalf;

    @ApiModelProperty(value = "排班具体时间：1-7 如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7 如果是下午，则是从1点到4点半")
    @TableField("work_time")
    private Integer workTime;

    @ApiModelProperty(value = "可预约数")
    @TableField("reserved_number")
    private Integer reservedNumber;

    @ApiModelProperty(value = "剩余预约数")
    @TableField("available_number")
    private Integer availableNumber;

    @ApiModelProperty(value = "挂号费")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "排班状态（-1.停诊 0.停约 1.可约）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
