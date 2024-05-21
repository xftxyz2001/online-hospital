package com.nwu.hospital.model.po;

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
 * 医生信息表
 * </p>
 *
 * @author author
 * @since 2024-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("doctor_info")
@ApiModel(value = "DoctorInfo对象", description = "医生信息表")
public class DoctorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "性别：1.男 0.女")
    private Integer gender;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "简介")
    private String introduce;
    @ApiModelProperty(value = "照片")
    private String picture;

    @ApiModelProperty(value = "所属医院")
    private Long hospitalId;

    @ApiModelProperty(value = "所属门诊")
    private Long outpatientId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "是否问诊(0.否 1.是)")
    private Integer isInquiry;

    @ApiModelProperty(value = "是否出诊(0.否 1.是)")
    private Integer isOutCall;

    @ApiModelProperty(value = "候诊人数")
    private Integer waitingNum;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String username;


}
