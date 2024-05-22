package com.nwu.hospital.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 22:41
 **/
@Data
@ApiModel(value = "app查询问诊医生列表Vo")
public class AppQueryInquiryDoctorListVo {

    private Long id;

    @ApiModelProperty(value = "医生姓名")
    private String name;
    @ApiModelProperty(value = "照片")
    private String picture;
    @ApiModelProperty(value = "职称")
    private String professionalTitle;
    @ApiModelProperty(value = "候诊人数")
    private Integer waitingNum;
}
