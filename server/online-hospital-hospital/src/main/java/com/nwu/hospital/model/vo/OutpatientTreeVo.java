package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 门诊树状信息
@Data
@Schema(description = "门诊树状信息")
public class OutpatientTreeVo {
    @Schema(description = "门诊id")
    private Long value;
    @Schema(description = "门诊名称")
    private String label;
}
