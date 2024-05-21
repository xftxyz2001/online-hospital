package com.nwu.inquiry.model.ws;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version v1.0
 * @ClassName: Message
 * @Description: 用于封装浏览器发送给服务端的消息数据
 * @Author: 黑马程序员
 */
@Data
@ToString
@ApiModel(value = "webSocket消息")
public class WsMessage {

    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;
}
