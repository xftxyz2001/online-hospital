package com.nwu.inquiry.model.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "app查询单个问诊申请Vo")
public class AppQueryOneApplicationVo {
    private Long id;
    private Long linkId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "就诊人姓名")
    private String patientName;

    @ApiModelProperty(value = "病情描述信息（300字以内）")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "门诊名称")
    private String outpatientName;


    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "状态（0.排队 1.进行 2.结束 3.取消）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "取消时间")
    private LocalDateTime cancelTime;

}
