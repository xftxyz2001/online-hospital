package com.nwu.hospital.model.dto.admin;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "修改门诊Dto")
@Data
public class UpdateOutpatientDto {


    @Schema(description = "编号")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "介绍")
    private String disc;
}
