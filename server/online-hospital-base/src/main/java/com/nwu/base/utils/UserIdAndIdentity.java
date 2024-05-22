package com.nwu.base.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdAndIdentity {
    private Long id;
    private Integer identity;
}
