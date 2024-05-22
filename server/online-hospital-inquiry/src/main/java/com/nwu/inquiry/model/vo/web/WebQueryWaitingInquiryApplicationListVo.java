package com.nwu.inquiry.model.vo.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/3 23:01
 **/
@Data
@ApiModel(value = "web查询所有等待问诊Vo")
public class WebQueryWaitingInquiryApplicationListVo {
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "就诊人id")
    private Long patientId;

    @ApiModelProperty(value = "就诊人姓名")
    private String patientName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
