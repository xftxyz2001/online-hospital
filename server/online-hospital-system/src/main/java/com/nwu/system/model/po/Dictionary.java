package com.nwu.system.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author author
 * @since 2024-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dictionary")
@ApiModel(value = "Dictionary对象", description = "数据字典")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据字典名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "数据字典代码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "数据字典项--json格式")
    @TableField("item_values")
    private String itemValues;


}
