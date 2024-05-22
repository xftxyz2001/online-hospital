package com.nwu.registration.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description AppScheduleVo
 * @DateTime 2024/3/18 21:24
 **/
@Data
@ApiModel(value = "AppScheduleVo对象，返回上午下午有哪些医生以及挂号情况")
public class AppScheduleVo {
    @ApiModelProperty(value = "医生id")

    private Long doctorId;

    @ApiModelProperty(value = "职称")
    private String professionalTitle;

    @ApiModelProperty(value = "医生名称")
    private String doctorName;

    @ApiModelProperty(value = "医生介绍")
    private String doctorIntroduce;
    @ApiModelProperty(value = "照片")
    private String picture;
    @ApiModelProperty(value = "排班时间（0.上午1.下午）")

    private Integer workHalf;
    @ApiModelProperty(value = "已预约数")

    private Integer hasReservedNumber;

    @ApiModelProperty(value = "剩余预约数")

    private Integer availableNumber;
}
