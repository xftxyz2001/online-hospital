package com.nwu.user.model.dto.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/23 21:38
 **/
@Data
@Schema(description = "添加就诊人Dto")
public class AddPatientDto {
    @Schema(description = "就诊人姓名")
    String name;
    @Schema(description = "证件类型")
    String certificatesType;
    @Schema(description = "证件号")
    String certificatesNo;
    @Schema(description = "性别，0为女，1为男")
    Integer sex;
    @Schema(description = "出生年月")
    private LocalDate birthdate;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "是否结婚 0.否 1.是")
    private Integer isMarry;

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
