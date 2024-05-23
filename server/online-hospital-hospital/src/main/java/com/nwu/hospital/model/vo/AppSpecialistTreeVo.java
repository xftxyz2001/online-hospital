package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

// 小程序专科树状信息
@Data
@Schema(description = "小程序专科树状信息")
public class AppSpecialistTreeVo {
    @Schema(description = "专科名称")
    private String text;
    @Schema(description = "包含的门诊")
    private List<AppOutpatientTreeVo> children;

}