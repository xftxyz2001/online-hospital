package com.nwu.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 分页查询参数
 * @DateTime 2024/3/1 13:28
 **/
@Data
public class PageParams {

    @ApiModelProperty("页码")
    private Long pageNo = 1L;
    @ApiModelProperty("每页记录数")
    private Long pageSize = 30L;
}
