package com.nwu.registration.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "添加报告Dto")
public class AddReportDto {

    private Long userId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    @ApiModelProperty(value = "报告名称")
    private String reportName;
    @ApiModelProperty(value = "文件地址")
    private String fileUrl;
}
