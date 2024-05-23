package com.nwu.inquiry.model.vo.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(description = "web查询所有等待问诊Vo")
public class WebQueryWaitingInquiryApplicationListVo {
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "就诊人id")
    private Long patientId;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
