package com.nwu.inquiry.model.vo.web;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "web查询单个已完成问诊详情")
public class WebQueryOneFinishedInquiryApplicationDetailVo {
    private Long id;
    private Long linkId;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "就诊人id")
    private Long patientId;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


    @Schema(description = "性别 0.女1.男")
    private Integer sex;

    @Schema(description = "出生年月")
    private LocalDate birthdate;

    @Schema(description = "是否结婚 0.否 1.是")
    private Integer isMarry;

    @Schema(description = "就诊卡号")
    private String cardNo;


    @Schema(description = "病情描述信息（300字以内）")
    private String description;

    @Schema(description = "图片")
    private String image;
}
