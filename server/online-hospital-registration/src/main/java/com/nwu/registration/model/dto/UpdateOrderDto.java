package com.nwu.registration.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "更新订单Dto")
@Data
public class UpdateOrderDto {
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "订单状态:0.未完成1.已完成 2.已超时3.已取消")
    private Integer orderStatus;
}
