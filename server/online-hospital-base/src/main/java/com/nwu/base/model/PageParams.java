package com.nwu.base.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 分页查询参数
@Data
public class PageParams {

    @Schema(description = "页码")
    private Long pageNo = 1L;
    @Schema(description = "每页记录数")
    private Long pageSize = 30L;
}
