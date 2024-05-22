package com.nwu.registration.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @Description 排班信息vo
 * @DateTime 2024/3/8 23:38
 **/
@Data
@ApiModel(value = "ScheduleVo对象")
public class ScheduleVo {

    private Long id;

    @ApiModelProperty(value = "医院id")

    private Long hospitalId;

    @ApiModelProperty(value = "门诊id")

    private Long outpatientId;

    @ApiModelProperty(value = "医生id")

    private Long doctorId;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "医生名称")
    private String doctorName;

    @ApiModelProperty(value = "医生介绍")
    private String doctorIntroduce;

    @ApiModelProperty(value = "照片")
    private String picture;
    @ApiModelProperty(value = "排班日期")

    private LocalDate workDate;

    @ApiModelProperty(value = "排班时间（0.上午1.下午）")

    private Integer workHalf;

    @ApiModelProperty(value = "排班具体时间：1-7 如果是上午，则是从8点到11点半，中间以半小时为一格，分别为1-7 如果是下午，则是从1点到4点半")

    private Integer workTime;

    @ApiModelProperty(value = "可预约数")

    private Integer reservedNumber;

    @ApiModelProperty(value = "剩余预约数")

    private Integer availableNumber;

    @ApiModelProperty(value = "挂号费")

    private BigDecimal amount;

    @ApiModelProperty(value = "排班状态（-1.停诊 0.停约 1.可约）")

    private Integer status;

    @ApiModelProperty(value = "创建时间")

    private LocalDateTime createTime;


}