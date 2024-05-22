package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "分页条件查询医生列表Vo")
@Data
public class DoctorInfoPageVo {


    @Schema(description = "编号")
    private Long id;
    @Schema(description = "医生姓名")
    private String name;
    @Schema(description = "职称")
    private String professionalTitle;
    @Schema(description = "所属医院")
    private String hospitalName;
    @Schema(description = "所属门诊")
    private String outpatientName;
    @Schema(description = "照片")
    private String picture;


}
