package com.nwu.hospital.model.dto.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 查询问诊医生列表Dto
@Data
@Schema(description = "查询问诊医生列表Dto")
public class QueryInquiryDoctorListDto {
    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    private Long outpatientId;
}
