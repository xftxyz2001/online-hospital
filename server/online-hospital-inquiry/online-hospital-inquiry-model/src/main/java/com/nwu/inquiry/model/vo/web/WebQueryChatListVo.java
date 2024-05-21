package com.nwu.inquiry.model.vo.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "web查询问诊聊天列表Vo")
public class WebQueryChatListVo {
    @ApiModelProperty(value = "聊天列表id")
    private Long listId;
    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;
    @ApiModelProperty(value = "接收方(患者)姓名")
    private String toUserName;
    @ApiModelProperty(value = "接收方(患者)头像")
    private String toUserPicture;
    @ApiModelProperty(value = "未读数")
    private Integer unread;
    @ApiModelProperty(value = "最后一条消息")
    private String lastMessage;
    @ApiModelProperty(value = "类型：0.文字 1.图片 2.无消息")
    private Integer type;
    @ApiModelProperty(value = "最后一条消息时间")
    private String lastTime;
}
