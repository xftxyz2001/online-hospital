package com.nwu.registration.model.clientspo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 分院信息
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

@Schema(description = "HospitalInfo对象-分院信息")
public class HospitalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "介绍")
    private String introduction;

    @Schema(description = "图片地址")
    private String picture;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "修改时间")
    private String modifiedTime;


}
