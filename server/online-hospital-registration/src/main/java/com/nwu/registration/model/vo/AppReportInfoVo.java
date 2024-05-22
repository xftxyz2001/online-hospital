package com.nwu.registration.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "App检查报告Vo")
public class AppReportInfoVo {

    private String patientName;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "报告名称")
    private String reportName;

    @Schema(description = "文件地址")
    private String fileUrl;
}
