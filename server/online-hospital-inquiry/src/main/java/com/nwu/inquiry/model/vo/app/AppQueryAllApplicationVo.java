package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "app查询所有问诊申请Vo")
public class AppQueryAllApplicationVo {
    private Long id;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "门诊名称")
    private String outpatientName;

    @Schema(description = "医生姓名")
    private String doctorName;

    @Schema(description = "状态（0.排队 1.进行 2.结束 3.取消）")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
