package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/6 18:02
 **/
@Schema(description = "app查询未读数Vo")
@Data
public class UnreadNumber {

    @Schema(description = "未读数,当有进行中问诊时有效")
    private Integer unread;

}
