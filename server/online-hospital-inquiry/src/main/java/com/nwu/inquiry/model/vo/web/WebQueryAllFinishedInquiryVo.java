package com.nwu.inquiry.model.vo.web;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "web已完成问诊列表Vo")
public class WebQueryAllFinishedInquiryVo {
    private Long id;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "病情描述信息（300字以内）")
    private String description;


    @Schema(description = "状态（0.排队 1.进行 2.结束 3.取消）")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
