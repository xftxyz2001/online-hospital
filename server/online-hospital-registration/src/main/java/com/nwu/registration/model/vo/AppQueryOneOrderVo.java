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
 * @DateTime 2024/3/25 14:45
 **/
@Data
@ApiModel(value = "查询单个订单Vo")
public class AppQueryOneOrderVo {
    private String id;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;


    @ApiModelProperty(value = "医院名称")
    private String hospitalName;


    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;

    private LocalDate reserveDate;

    @ApiModelProperty(value = "预约费")
    private BigDecimal amount;

    @ApiModelProperty(value = "订单状态:0.未完成1.已完成")
    private Integer orderStatus;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    private String patientName;

    private String patientPhone;

    private Integer reserveHalf;

    private Integer reserveTime;

}
