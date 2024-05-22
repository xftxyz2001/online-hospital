package com.nwu.user.model.vo.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/24 9:07
 **/
@Data
@Builder
public class AppQueryOnePatientVo {
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "性别 0.女1.男")
    private Integer sex;
    @Schema(description = "证件类型")
    private String certificatesType;

    @Schema(description = "证件编号")
    private String certificatesNo;

    @Schema(description = "就诊卡号")
    private String cardNo;
    @Schema(description = "出生年月")
    private LocalDate birthdate;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "详情地址")
    private String addressDetail;

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
