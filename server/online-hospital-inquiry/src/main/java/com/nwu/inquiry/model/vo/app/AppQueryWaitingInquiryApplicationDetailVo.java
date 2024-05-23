package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(description = "app查询等待问诊详情Vo")
public class AppQueryWaitingInquiryApplicationDetailVo {
    private Long id;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "病情描述信息（300字以内）")
    private String description;

    @Schema(description = "门诊名称")
    private String outpatientName;


    @Schema(description = "医生姓名")
    private String doctorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "候诊人数")
    private Integer waitingNum;
}
