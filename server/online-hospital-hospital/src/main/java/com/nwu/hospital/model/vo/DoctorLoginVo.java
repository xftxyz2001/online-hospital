package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

// 医生登录Vo
@Data
@Builder
@Schema(description = "医生登录Vo")
public class DoctorLoginVo {
    private Long id;

    @Schema(description = "医生token")
    private String token;
}
