package com.nwu.registration.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "添加报告Dto")
public class AddReportDto {

    private Long userId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    @Schema(description = "报告名称")
    private String reportName;
    @Schema(description = "文件地址")
    private String fileUrl;
}
