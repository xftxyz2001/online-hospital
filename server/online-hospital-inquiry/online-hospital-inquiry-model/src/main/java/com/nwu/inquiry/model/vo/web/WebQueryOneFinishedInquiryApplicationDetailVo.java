package com.nwu.inquiry.model.vo.web;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "web查询单个已完成问诊详情")
public class WebQueryOneFinishedInquiryApplicationDetailVo {
    private Long id;
    private Long linkId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "就诊人id")
    private Long patientId;

    @ApiModelProperty(value = "就诊人姓名")
    private String patientName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "性别 0.女1.男")
    private Integer sex;

    @ApiModelProperty(value = "出生年月")
    private LocalDate birthdate;

    @ApiModelProperty(value = "是否结婚 0.否 1.是")
    private Integer isMarry;

    @ApiModelProperty(value = "就诊卡号")
    private String cardNo;


    @ApiModelProperty(value = "病情描述信息（300字以内）")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;
}
