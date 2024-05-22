package com.nwu.inquiry.model.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "app单条问诊聊天信息Vo")
public class AppChatMessageVo {
    @ApiModelProperty(value = "身份：0.用户 1.医生")
    private Integer Identity;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private String sendTime;

    @ApiModelProperty(value = "类型：0.文字 1.图片")
    private Integer type;
}