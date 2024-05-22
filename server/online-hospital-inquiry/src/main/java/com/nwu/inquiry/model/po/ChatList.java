package com.nwu.inquiry.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 聊天列表表
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_list")
@ApiModel(value = "ChatList对象", description = "聊天列表表")
public class ChatList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "发送方id")
    private Long fromUserId;

    @ApiModelProperty(value = "发送方姓名")
    private String fromUserName;

    @ApiModelProperty(value = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;

    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;

    @ApiModelProperty(value = "接收方姓名")
    private String toUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "发送方是否在窗口(0.否 1.是)")
    private Integer fromWindow;

    @ApiModelProperty(value = "接收方是否在窗口(0.否 1.是)")
    private Integer toWindow;

    @ApiModelProperty(value = "未读数")
    private Integer unread;

    @ApiModelProperty(value = "列表状态(0.未删除 1.已删除)")
    private Integer status;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "最后一条消息时间")
    private LocalDateTime lastMessageTime;


}
