package com.nwu.hospital.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "更新医生Dto")
@Data
public class UpdateDoctorDto {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "医生姓名")
    private String name;

    @Schema(description = "性别：1.男 0.女")
    private String gender;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "简介")
    private String introduce;

    @Schema(description = "照片")
    private String picture;

    @Schema(description = "在线问诊:0.否 1.是")

    private String isInquiry;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;


}
