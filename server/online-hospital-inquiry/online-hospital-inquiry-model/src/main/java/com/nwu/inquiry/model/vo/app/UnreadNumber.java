package com.nwu.inquiry.model.vo.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/4/6 18:02
 **/
@ApiModel(value = "app查询未读数Vo")
@Data
@ToString
public class UnreadNumber {

    @ApiModelProperty(value = "未读数,当有进行中问诊时有效")
    private Integer unread;

}
