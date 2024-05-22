package com.nwu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.system.model.po.Dictionary;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-03
 */
public interface IDictionaryService extends IService<Dictionary> {

    Result<Dictionary> queryByCode(String code);
}
