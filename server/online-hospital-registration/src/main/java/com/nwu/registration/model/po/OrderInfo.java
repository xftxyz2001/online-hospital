package com.nwu.registration.model.po;

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
@ApiModel(value = "OrderInfo对象", description = "")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;

    @ApiModelProperty(value = "医院编号")
    private Long hospitalId;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "门诊编号")
    private Long outpatientId;

    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;

    private LocalDate reserveDate;

    private Integer reserveHalf;

    private Integer reserveTime;

    private String patientName;

    private String patientPhone;

    @ApiModelProperty(value = "预约费")
    private BigDecimal amount;

    @ApiModelProperty(value = "订单状态:0.未完成1.已完成 2.已超时3.已取消")
    private Integer orderStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;

    @ApiModelProperty(value = "排班id")
    private Long scheduleId;

    @ApiModelProperty(value = "医生编号")
    private Long doctorId;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    private Long patientId;


}
