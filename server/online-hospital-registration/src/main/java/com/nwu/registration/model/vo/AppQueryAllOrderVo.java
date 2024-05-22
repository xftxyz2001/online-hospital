package com.nwu.registration.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/25 14:57
 **/
@Data
@Schema(description = "查询所有订单Vo")
public class AppQueryAllOrderVo {
    private String id;

    @Schema(description = "订单交易号")
    private String outTradeNo;


    @Schema(description = "门诊名称")
    private String outpatientName;

    private String patientName;

    @Schema(description = "医生姓名")
    private String doctorName;
}
