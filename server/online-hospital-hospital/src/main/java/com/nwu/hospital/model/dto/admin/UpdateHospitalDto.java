package com.nwu.hospital.model.dto.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "更新医院Dto")
@Data
public class UpdateHospitalDto {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "介绍")
    private String introduction;

    @Schema(description = "图片地址")
    private String picture;
}
