package com.nwu.hospital.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "App首字母排序门诊列表")
@Data
public class AppOutpatientListOrderByLetter {
    @ApiModelProperty(value = "首字母列表")
    List<String> letterList;

    @ApiModelProperty(value = "门诊列表")
    List<List<AppOutpatientListVo>> outpatientList;
}
