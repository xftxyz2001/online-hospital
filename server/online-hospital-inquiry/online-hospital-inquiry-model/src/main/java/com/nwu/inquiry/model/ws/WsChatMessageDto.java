package com.nwu.inquiry.model.ws;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel(value = "ws添加消息Dto")
@Data
@ToString
public class WsChatMessageDto {

    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "发送方id")
    private Long fromUserId;

    @ApiModelProperty(value = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;


    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;


    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "类型：0.文字 1.图片")
    private Integer type;

}