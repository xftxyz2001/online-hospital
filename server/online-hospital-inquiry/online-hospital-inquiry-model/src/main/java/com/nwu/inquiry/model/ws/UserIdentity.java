package com.nwu.inquiry.model.ws;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "身份标识")
public class UserIdentity {
    @ApiModelProperty(value = "id")
    private Long userId;

    @ApiModelProperty(value = "身份：0.用户 1.医生")
    private Integer userIdentity;
}
