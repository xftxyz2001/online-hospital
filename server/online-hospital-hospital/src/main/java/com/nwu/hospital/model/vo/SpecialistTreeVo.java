package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description 专科树状信息
 * @DateTime 2024/3/8 18:26
 **/
@Data
@Schema(description = "专科树状信息")
public class SpecialistTreeVo {
    @Schema(description = "专科名称")
    private String label;
    @Schema(description = "包含的门诊")
    private List<OutpatientTreeVo> children;

}
