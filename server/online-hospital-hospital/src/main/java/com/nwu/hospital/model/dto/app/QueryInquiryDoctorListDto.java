package com.nwu.hospital.model.dto.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 22:35
 **/
@Data
@Schema(description = "查询问诊医生列表Dto")
public class QueryInquiryDoctorListDto {
    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    private Long outpatientId;
}
