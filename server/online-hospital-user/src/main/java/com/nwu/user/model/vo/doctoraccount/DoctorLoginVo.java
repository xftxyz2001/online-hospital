package com.nwu.user.model.vo.doctoraccount;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/1 12:32
 **/
@Data
@Builder
@Schema(description = "医生登录Vo")
public class DoctorLoginVo {
    private Long id;

    @Schema(description = "医生token")
    private String token;
}
