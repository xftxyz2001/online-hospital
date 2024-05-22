package com.nwu.inquiry.model.clientspo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 就诊人表
 * </p>
 *
 * @author author
 * @since 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("patient_info")
@Schema(description = "PatientInfo对象-就诊人表")
public class PatientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "证件类型")
    private String certificatesType;

    @Schema(description = "证件编号")
    private String certificatesNo;

    @Schema(description = "性别 0.女1.男")
    private Integer sex;

    @Schema(description = "出生年月")
    private LocalDate birthdate;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "是否结婚 0.否 1.是")
    private Integer isMarry;

    @Schema(description = "省")
    private String provinceName;

    @Schema(description = "市")
    private String cityName;

    @Schema(description = "区")
    private String districtName;

    @Schema(description = "详情地址")
    private String address;

    @Schema(description = "联系人姓名")
    private String contactsName;

    @Schema(description = "联系人证件类型")
    private String contactsCertificatesType;

    @Schema(description = "联系人证件号")
    private String contactsCertificatesNo;

    @Schema(description = "联系人手机")
    private String contactsPhone;

    @Schema(description = "就诊卡号")
    private String cardNo;

    @Schema(description = "状态（0：默认 1：已认证）")
    private Integer status;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "逻辑删除(1:已删除，0:未删除)")
    private Integer isDeleted;


}
