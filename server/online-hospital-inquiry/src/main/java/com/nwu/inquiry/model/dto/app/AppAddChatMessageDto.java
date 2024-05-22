package com.nwu.inquiry.model.dto.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "web发送消息Dto")
@Data
public class AppAddChatMessageDto {

    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;
    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "类型：0.文字 1.图片")
    private Integer type;

}
