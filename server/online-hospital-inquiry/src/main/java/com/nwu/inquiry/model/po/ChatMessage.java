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

/**
 * <p>
 * 聊天内容详情表
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_message")
@Schema(description = "ChatMessage对象-聊天内容详情表")
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "发送方id")
    private Long fromUserId;

    @Schema(description = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;

    @Schema(description = "发送方姓名")
    private String fromUserName;

    @Schema(description = "接收方id")
    private Long toUserId;

    @Schema(description = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;

    @Schema(description = "接收方姓名")
    private String toUserName;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "发送时间")
    private LocalDateTime sendTime;

    @Schema(description = "类型：0.文字 1.图片")
    private Integer type;

    @Schema(description = "是否是最后一条消息:0.否 1.是")
    private Integer isLatest;


}
