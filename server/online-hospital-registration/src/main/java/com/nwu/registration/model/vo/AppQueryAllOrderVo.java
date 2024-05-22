package com.nwu.registration.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/25 14:57
 **/
@Data
@ApiModel(value = "查询所有订单Vo")
public class AppQueryAllOrderVo {
    private String id;

    @ApiModelProperty(value = "订单交易号")
    private String outTradeNo;


    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;

    private String patientName;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;
}
