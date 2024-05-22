package com.nwu.hospital.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "添加门诊Dto")
@Data
public class AddOutpatientDto {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "所属医院")
    private Long hospitalId;

    @Schema(description = "所属专科")
    private Long specialistId;

    @Schema(description = "介绍")
    private String disc;

}
