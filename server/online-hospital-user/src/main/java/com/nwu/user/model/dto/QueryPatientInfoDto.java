package com.nwu.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/7 17:09
 **/
@Schema(description = "请求就诊人查询参数")
@Data
public class QueryPatientInfoDto {
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "性别")
    private String sex;

}
