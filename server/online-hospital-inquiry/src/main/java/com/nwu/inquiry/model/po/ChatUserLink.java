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
@TableName("chat_user_link")
@Schema(description = "ChatUserLink对象-用户聊天关系表")
public class ChatUserLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @Schema(description = "接收方（患者）姓名")
    private String toUserName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "是否结束 0.否 1.是")
    private Integer isFinished;


}
