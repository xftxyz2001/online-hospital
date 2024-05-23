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
@TableName("inquiry_application")
@Schema(description = "InquiryApplication对象")
public class InquiryApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "就诊人id")
    private Long patientId;

    @Schema(description = "就诊人姓名")
    private String patientName;

    @Schema(description = "病情描述信息（300字以内）")
    private String description;

    @Schema(description = "图片")
    private String image;

    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "医院名称")
    private String hospitalName;

    @Schema(description = "门诊id")
    private Long outpatientId;

    @Schema(description = "门诊名称")
    private String outpatientName;

    @Schema(description = "医生id")
    private Long doctorId;

    @Schema(description = "医生姓名")
    private String doctorName;

    @Schema(description = "状态（0.排队 1.进行 2.结束 3.取消）")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "剩余聊天次数")
    private Integer remainNumber;

}
