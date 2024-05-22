package com.nwu.user.model.vo.patient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/23 23:57
 **/
@Data
@Builder
@ApiModel(value = "查询所有就诊人Vo")
public class AppQueryAllPatientVo {
    @ApiModelProperty(value = "编号")
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "就诊卡号")
    private String cardNo;
}
