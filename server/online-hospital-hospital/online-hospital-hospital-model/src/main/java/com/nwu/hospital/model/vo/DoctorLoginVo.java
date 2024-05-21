package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/4/1 12:32
 **/
@Data
@Builder
@ApiModel(value = "医生登录Vo")
public class DoctorLoginVo {
    private Long id;

    @ApiModelProperty(value = "医生token")
    private String token;
}
