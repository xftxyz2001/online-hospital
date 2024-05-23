package com.nwu.inquiry.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "app查询未读数Vo")
@Data
public class UnreadNumber {

    @Schema(description = "未读数,当有进行中问诊时有效")
    private Integer unread;

}
