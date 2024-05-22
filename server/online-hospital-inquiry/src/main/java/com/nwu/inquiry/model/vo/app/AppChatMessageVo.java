package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "app单条问诊聊天信息Vo")
public class AppChatMessageVo {
    @Schema(description = "身份：0.用户 1.医生")
    private Integer Identity;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "发送时间")
    private String sendTime;

    @Schema(description = "类型：0.文字 1.图片")
    private Integer type;
}