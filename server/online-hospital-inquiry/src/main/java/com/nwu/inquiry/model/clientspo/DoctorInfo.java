package com.nwu.inquiry.model.clientspo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Schema(description = "DoctorInfo对象")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DoctorInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    @Schema(description = "医生姓名")
    private String name;

    @Schema(description = "性别：1.男 0.女")
    private Integer gender;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "简介")
    private String introduce;
    @Schema(description = "照片")
    private String picture;

    @Schema(description = "所属医院")
    private Long hospitalId;

    @Schema(description = "所属门诊")
    private Long outpatientId;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "修改时间")
    private String modifiedTime;

    @Schema(description = "是否问诊(0.否 1.是)")
    private Integer isInquiry;

    @Schema(description = "是否出诊(0.否 1.是)")
    private Integer isOutCall;

    @Schema(description = "候诊人数")
    private Integer waitingNum;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;


}