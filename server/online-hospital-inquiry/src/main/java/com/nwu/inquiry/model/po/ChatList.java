package com.nwu.inquiry.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_list")
@Schema(description = "ChatList对象-聊天列表表")
public class ChatList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "发送方id")
    private Long fromUserId;

    @Schema(description = "发送方姓名")
    private String fromUserName;

    @Schema(description = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;

    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;

    @Schema(description = "接收方姓名")
    private String toUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "发送方是否在窗口(0.否 1.是)")
    private Integer fromWindow;

    @Schema(description = "接收方是否在窗口(0.否 1.是)")
    private Integer toWindow;

    @Schema(description = "未读数")
    private Integer unread;

    @Schema(description = "列表状态(0.未删除 1.已删除)")
    private Integer status;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "最后一条消息时间")
    private LocalDateTime lastMessageTime;


}
