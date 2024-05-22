package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "web聊天消息vo")
public class AppQueryChatMessageVo {
    @Schema(description = "用户聊天关系表id")
    private Long linkId;

    @Schema(description = "问诊申请id")
    private Long inquiryApplicationId;

    @Schema(description = "医生姓名")
    private String doctorName;

    @Schema(description = "医生id")
    private Long doctorId;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "简介")
    private String introduce;

    @Schema(description = "病情描述")
    private String description;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "发送方头像")
    private String fromUserPicture;
    ;

    @Schema(description = "接收方(医生)头像")
    private String toUserPicture;

    @Schema(description = "剩余聊天次数")
    private Integer remainNumber;
    @Schema(description = "聊天信息列表")
    private List<AppChatMessageVo> messageVos;

}
