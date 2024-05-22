package com.nwu.inquiry.model.ws;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "ws添加消息Dto")
@Data
public class WsChatMessageDto {

    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "发送方id")
    private Long fromUserId;

    @Schema(description = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;


    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;


    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "类型：0.文字 1.图片")
    private Integer type;

}