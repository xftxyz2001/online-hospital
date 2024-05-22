package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "app查询问诊聊天列表Vo")
public class AppQueryChatListVo {
    @Schema(description = "聊天列表id")
    private Long listId;
    @Schema(description = "用户聊天关系表id")
    private Long linkId;
    @Schema(description = "接收方(医生)姓名")
    private String toUserName;
    @Schema(description = "接收方(医生)头像")
    private String toUserPicture;
    @Schema(description = "未读数")
    private Integer unread;
    @Schema(description = "最后一条消息")
    private String lastMessage;
    @Schema(description = "类型：0.文字 1.图片 2.无消息")
    private Integer type;
    @Schema(description = "最后一条消息时间")
    private String lastTime;
}