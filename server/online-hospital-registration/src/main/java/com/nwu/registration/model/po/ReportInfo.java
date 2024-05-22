package com.nwu.registration.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("report_info")
@Schema(description = "ReportInfo对象")
public class ReportInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long patientId;

    private Long doctorId;

    private String patientName;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "报告名称")
    private String reportName;

    @Schema(description = "文件地址")
    private String fileUrl;
}
