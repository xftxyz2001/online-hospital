package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "门诊列表vo")
@Data
public class AppOutpatientListVo {

    private Long id;

    @Schema(description = "名称")
    private String name;
}
