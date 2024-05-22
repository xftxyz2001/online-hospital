package com.nwu.hospital.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "添加医生Dto")
@Data
public class AddDoctorDto {
    @Schema(description = "医生姓名")
    private String name;

    @Schema(description = "性别：1.男 0.女")
    private Integer gender;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "照片")
    private String picture;

    @Schema(description = "简介")
    private String introduce;

    @Schema(description = "所属医院")
    private Long hospitalId;

    @Schema(description = "所属门诊")
    private Long outpatientId;

    @Schema(description = "在线问诊:0.否 1.是")

    private Integer isInquiry;
}
