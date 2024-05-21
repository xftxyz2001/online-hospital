package com.nwu.inquiry.model.dto.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel(value = "web发送消息Dto")
@Data
@ToString
public class AppAddChatMessageDto {

    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;
    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "类型：0.文字 1.图片")
    private Integer type;

}
