package com.nwu.inquiry.model.clientspo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2024-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
@Schema(description = "UserInfo对象")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "昵称")
    private String username;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "状态：0.禁用1.启用")
    private Integer status;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "上次修改时间")
    private String modifiedTime;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "微信openID")
    private String openId;

    @Schema(description = "是否逻辑删除 0.否 1.是")
    private Integer isDeleted;


}

