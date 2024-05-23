package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

// 专科树状信息
@Data
@Schema(description = "专科树状信息")
public class SpecialistTreeVo {
    @Schema(description = "专科名称")
    private String label;
    @Schema(description = "包含的门诊")
    private List<OutpatientTreeVo> children;

}
