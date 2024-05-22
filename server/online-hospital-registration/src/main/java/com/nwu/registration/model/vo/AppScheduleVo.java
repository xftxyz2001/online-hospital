package com.nwu.registration.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description AppScheduleVo
 * @DateTime 2024/3/18 21:24
 **/
@Data
@Schema(description = "AppScheduleVo对象，返回上午下午有哪些医生以及挂号情况")
public class AppScheduleVo {
    @Schema(description = "医生id")

    private Long doctorId;

    @Schema(description = "职称")
    private String professionalTitle;

    @Schema(description = "医生名称")
    private String doctorName;

    @Schema(description = "医生介绍")
    private String doctorIntroduce;
    @Schema(description = "照片")
    private String picture;
    @Schema(description = "排班时间（0.上午1.下午）")

    private Integer workHalf;
    @Schema(description = "已预约数")

    private Integer hasReservedNumber;

    @Schema(description = "剩余预约数")

    private Integer availableNumber;
}
