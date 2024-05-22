package com.nwu.inquiry.model.vo.web;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@ApiModel(value = "web已完成问诊列表Vo")
public class WebQueryAllFinishedInquiryVo {
    private Long id;

    @ApiModelProperty(value = "就诊人姓名")
    private String patientName;

    @ApiModelProperty(value = "病情描述信息（300字以内）")
    private String description;


    @ApiModelProperty(value = "状态（0.排队 1.进行 2.结束 3.取消）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
