package com.nwu.system.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dictionary")
@Schema(description = "Dictionary对象-数据字典")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "数据字典名称")
    @TableField("name")
    private String name;

    @Schema(description = "数据字典代码")
    @TableField("code")
    private String code;

    @Schema(description = "数据字典项--json格式")
    @TableField("item_values")
    private String itemValues;


}
