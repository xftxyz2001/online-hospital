package com.nwu.inquiry.model.vo.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@ApiModel(value = "web聊天消息vo")
public class WebQueryChatMessageVo {
    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "接收方姓名")
    private String toUserName;

    @ApiModelProperty(value = "发送方id")
    private Long fromUserId;

    @ApiModelProperty(value = "发送方姓名")
    private String fromUserName;
    @ApiModelProperty(value = "病情描述")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "发送方头像")
    private String fromUserPicture;

    @ApiModelProperty(value = "接收方(医生)头像")
    private String toUserPicture;

    @ApiModelProperty(value = "剩余聊天次数")
    private Integer remainNumber;


    @ApiModelProperty(value = "聊天信息列表")
    private List<WebChatMessageVo> messageVos;
}
