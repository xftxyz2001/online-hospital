package com.nwu.inquiry.model.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "web聊天消息vo")
public class AppQueryChatMessageVo {
    @ApiModelProperty(value = "用户聊天关系表id")
    private Long linkId;

    @ApiModelProperty(value = "问诊申请id")
    private Long inquiryApplicationId;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "医生id")
    private Long doctorId;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "简介")
    private String introduce;

    @ApiModelProperty(value = "病情描述")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "发送方头像")
    private String fromUserPicture;
    ;

    @ApiModelProperty(value = "接收方(医生)头像")
    private String toUserPicture;

    @ApiModelProperty(value = "剩余聊天次数")
    private Integer remainNumber;
    @ApiModelProperty(value = "聊天信息列表")
    private List<AppChatMessageVo> messageVos;

}
