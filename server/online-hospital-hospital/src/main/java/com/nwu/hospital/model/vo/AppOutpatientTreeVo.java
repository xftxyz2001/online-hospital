package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 小程序门诊树状信息
@Data
@Schema(description = "小程序门诊树状信息")
public class AppOutpatientTreeVo {
    @Schema(description = "门诊id")
    private Long id;
    @Schema(description = "门诊名称")
    private String text;
}
