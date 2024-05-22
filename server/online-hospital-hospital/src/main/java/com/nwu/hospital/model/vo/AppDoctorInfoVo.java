package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "App单个医生信息Vo")
@Data
public class AppDoctorInfoVo {
    @Schema(description = "医生姓名")
    private String name;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "简介")
    private String introduce;
    @Schema(description = "照片")
    private String picture;

    @Schema(description = "所属医院")
    private String hospitalName;

    @Schema(description = "所属门诊")
    private String outpatientName;

    @Schema(description = "是否问诊(0.否 1.是)")
    private Integer isInquiry;

}
