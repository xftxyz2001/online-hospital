package com.nwu.inquiry.model.ws;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


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
