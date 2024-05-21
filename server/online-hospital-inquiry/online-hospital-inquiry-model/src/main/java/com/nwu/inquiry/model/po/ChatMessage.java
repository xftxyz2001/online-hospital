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
@ApiModel(value = "ChatMessage对象", description = "聊天内容详情表")
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "发送方id")
    private Long fromUserId;

    @ApiModelProperty(value = "发送方身份：0.用户 1.医生")
    private Integer fromUserIdentity;

    @ApiModelProperty(value = "发送方姓名")
    private String fromUserName;

    @ApiModelProperty(value = "接收方id")
    private Long toUserId;

    @ApiModelProperty(value = "接收方身份：0.用户 1.医生")
    private Integer toUserIdentity;

    @ApiModelProperty(value = "接收方姓名")
    private String toUserName;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "类型：0.文字 1.图片")
    private Integer type;

    @ApiModelProperty(value = "是否是最后一条消息:0.否 1.是")
    private Integer isLatest;


}
