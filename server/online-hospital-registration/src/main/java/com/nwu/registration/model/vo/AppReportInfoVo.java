package com.nwu.registration.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "App检查报告Vo")
public class AppReportInfoVo {

    private String patientName;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "报告名称")
    private String reportName;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;
}
