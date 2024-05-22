package com.nwu.inquiry.model.ws;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version v1.0
 * @ClassName: Message
 * @Description: 用于封装浏览器发送给服务端的消息数据
 * @Author: 黑马程序员
 */
@Data
@Schema(description = "webSocket消息")
public class WsMessage {

    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;
}
