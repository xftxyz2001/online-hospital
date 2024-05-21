package com.nwu.user.model.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/3/23 18:09
 **/
@Data
@Builder
@ApiModel(value = "请求用户名Vo")
public class QueryUsernameVo {
    @ApiModelProperty(value = "用户名")
    String username;
}
