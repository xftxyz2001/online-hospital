package com.nwu.hospital.model.po;

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
@TableName("hospital_outpatient")
@Schema(description = "HospitalOutpatient对象-医院-门诊对应表")
public class HospitalOutpatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "医院id")
    private Long hospitalId;

    @Schema(description = "门诊id")
    private Long outpatientId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
