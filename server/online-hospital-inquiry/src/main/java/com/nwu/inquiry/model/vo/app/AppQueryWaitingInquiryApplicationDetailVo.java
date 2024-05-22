package com.nwu.inquiry.model.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/6 21:53
 **/
@Data
@ApiModel(value = "app查询等待问诊详情Vo")
public class AppQueryWaitingInquiryApplicationDetailVo {
    private Long id;

    @ApiModelProperty(value = "就诊人姓名")
    private String patientName;

    @ApiModelProperty(value = "病情描述信息（300字以内）")
    private String description;

    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;


    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "候诊人数")
    private Integer waitingNum;
}
