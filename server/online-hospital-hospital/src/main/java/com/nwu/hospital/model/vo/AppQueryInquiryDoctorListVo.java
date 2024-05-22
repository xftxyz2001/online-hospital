package com.nwu.hospital.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 22:41
 **/
@Data
@Schema(description = "app查询问诊医生列表Vo")
public class AppQueryInquiryDoctorListVo {

    private Long id;

    @Schema(description = "医生姓名")
    private String name;
    @Schema(description = "照片")
    private String picture;
    @Schema(description = "职称")
    private String professionalTitle;
    @Schema(description = "候诊人数")
    private Integer waitingNum;
}
