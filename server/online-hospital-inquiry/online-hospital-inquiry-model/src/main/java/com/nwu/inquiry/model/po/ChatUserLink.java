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
 * 用户聊天关系表
 * </p>
 *
 * @author author
 * @since 2024-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_user_link")
@ApiModel(value = "ChatUserLink对象", description = "用户聊天关系表")
public class ChatUserLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty(value = "接收方（患者）姓名")
    private String toUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "是否结束 0.否 1.是")
    private Integer isFinished;


}
