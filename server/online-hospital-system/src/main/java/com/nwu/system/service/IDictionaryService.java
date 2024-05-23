package com.nwu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.system.model.po.Dictionary;


public interface IDictionaryService extends IService<Dictionary> {

    Result<Dictionary> queryByCode(String code);
}
