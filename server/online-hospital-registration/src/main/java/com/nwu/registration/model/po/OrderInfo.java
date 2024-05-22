package com.nwu.registration.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2024-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_info")
@Schema(description = "OrderInfo对象")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    @Schema(description = "订单交易号")
    private String outTradeNo;

    @Schema(description = "医院编号")
    private Long hospitalId;

    @Schema(description = "医院名称")
    private String hospitalName;

    @Schema(description = "门诊编号")
    private Long outpatientId;

    @Schema(description = "门诊名称")
    private String outpatientName;

    private LocalDate reserveDate;

    private Integer reserveHalf;

    private Integer reserveTime;

    private String patientName;

    private String patientPhone;

    @Schema(description = "预约费")
    private BigDecimal amount;

    @Schema(description = "订单状态:0.未完成1.已完成 2.已超时3.已取消")
    private Integer orderStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;

    @Schema(description = "排班id")
    private Long scheduleId;

    @Schema(description = "医生编号")
    private Long doctorId;

    @Schema(description = "医生姓名")
    private String doctorName;

    private Long patientId;


}
