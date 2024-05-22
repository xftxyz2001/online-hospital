package com.nwu.user.model.dto.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "修改就诊人Dto")
public class UpdatePatientDto {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "省")
    private String provinceName;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "区")
    private String districtName;

    @Schema(description = "地区码")
    private String areaCode;

    @Schema(description = "详情地址")
    private String address;
}
