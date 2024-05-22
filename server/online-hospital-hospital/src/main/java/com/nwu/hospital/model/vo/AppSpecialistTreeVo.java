package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description 小程序专科树状信息
 * @DateTime 2024/3/17 16:13
 **/
@Data
@Schema(description = "小程序专科树状信息")
public class AppSpecialistTreeVo {
    @Schema(description = "专科名称")
    private String text;
    @Schema(description = "包含的门诊")
    private List<AppOutpatientTreeVo> children;

}