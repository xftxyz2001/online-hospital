package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "更新订单Dto")
@Data
public class UpdateOrderDto {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "订单状态:0.未完成1.已完成 2.已超时3.已取消")
    private Integer orderStatus;
}
