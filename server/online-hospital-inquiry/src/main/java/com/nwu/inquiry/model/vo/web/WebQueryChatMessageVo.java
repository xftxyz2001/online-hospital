package com.nwu.inquiry.model.vo.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "web聊天消息vo")
public class WebQueryChatMessageVo {
    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "接收方姓名")
    private String toUserName;

    @Schema(description = "发送方id")
    private Long fromUserId;

    @Schema(description = "发送方姓名")
    private String fromUserName;
    @Schema(description = "病情描述")
    private String description;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "发送方头像")
    private String fromUserPicture;

    @Schema(description = "接收方(医生)头像")
    private String toUserPicture;

    @Schema(description = "剩余聊天次数")
    private Integer remainNumber;


    @Schema(description = "聊天信息列表")
    private List<WebChatMessageVo> messageVos;
}
