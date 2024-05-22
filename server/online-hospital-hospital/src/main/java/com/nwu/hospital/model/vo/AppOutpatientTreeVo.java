package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description 小程序门诊树状信息
 * @DateTime 2024/3/17 16:12
 **/
@Data
@Schema(description = "小程序门诊树状信息")
public class AppOutpatientTreeVo {
    @Schema(description = "门诊id")
    private Long id;
    @Schema(description = "门诊名称")
    private String text;
}
