package com.nwu.user.model.vo.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "查询所有就诊人Vo")
public class AppQueryAllPatientVo {
    @Schema(description = "编号")
    private Long id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "就诊卡号")
    private String cardNo;
}
