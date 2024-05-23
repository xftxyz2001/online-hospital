package com.nwu.inquiry.model.dto.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "添加问诊Dto")
public class AddInquiryApplicationDto {

    @Schema(description = "就诊人id")
    private Long patientId;

    @Schema(description = "病情描述信息（300字以内）")
    private String description;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    private Long outpatientId;

    @Schema(description = "医生id")
    private Long doctorId;
}
