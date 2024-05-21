package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "门诊列表vo")
@Data
public class AppOutpatientListVo {

    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;
}
