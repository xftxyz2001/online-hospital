package com.nwu.hospital.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "添加专科Dto")
@Data
public class AddSpecialistDto {
    @Schema(description = "所属医院")
    private Long hospitalId;

    @Schema(description = "专科名称")
    private String name;

    @Schema(description = "介绍")
    private String disc;
}
