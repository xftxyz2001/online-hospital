package com.nwu.hospital.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "App首字母排序门诊列表")
@Data
public class AppOutpatientListOrderByLetter {
    @Schema(description = "首字母列表")
    List<String> letterList;

    @Schema(description = "门诊列表")
    List<List<AppOutpatientListVo>> outpatientList;
}
