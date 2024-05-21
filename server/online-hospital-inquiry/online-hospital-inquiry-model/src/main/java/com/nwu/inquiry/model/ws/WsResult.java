package com.nwu.inquiry.model.ws;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "webSocket结果")
public class WsResult {
    @ApiModelProperty(value = "发送方id")
    private Long fromUserId;

    @ApiModelProperty(value = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;

    @ApiModelProperty(value = "消息内容")
    private String content;
}
