package com.nwu.registration.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Schema(description = "查询单个订单Vo")
public class AppQueryOneOrderVo {
    private String id;

    @Schema(description = "订单交易号")
    private String outTradeNo;


    @Schema(description = "医院名称")
    private String hospitalName;


    @Schema(description = "门诊名称")
    private String outpatientName;

    private LocalDate reserveDate;

    @Schema(description = "预约费")
    private BigDecimal amount;

    @Schema(description = "订单状态:0.未完成1.已完成")
    private Integer orderStatus;

    private LocalDateTime createTime;

    @Schema(description = "医生姓名")
    private String doctorName;

    private String patientName;

    private String patientPhone;

    private Integer reserveHalf;

    private Integer reserveTime;

}
