package com.nwu.base.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 分页查询参数
 * @DateTime 2024/3/1 13:28
 **/
@Data
public class PageParams {

    @Schema(description = "页码")
    private Long pageNo = 1L;
    @Schema(description = "每页记录数")
    private Long pageSize = 30L;
}
