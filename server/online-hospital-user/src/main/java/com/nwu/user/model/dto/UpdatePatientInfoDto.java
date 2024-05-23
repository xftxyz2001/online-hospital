package com.nwu.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Schema(description = "修改就诊人信息参数模型")
public class UpdatePatientInfoDto {
    @Schema(description = "编号")
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别 0.女1.男")
    private Integer sex;

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

    @Schema(description = "联系人姓名")
    private String contactsName;

    @Schema(description = "联系人手机")
    private String contactsPhone;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
