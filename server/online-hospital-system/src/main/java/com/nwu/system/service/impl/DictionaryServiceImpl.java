package com.nwu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.model.Result;
import com.nwu.system.mapper.DictionaryMapper;
import com.nwu.system.model.po.Dictionary;
import com.nwu.system.service.IDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public Result<Dictionary> queryByCode(String code) {
        LambdaQueryWrapper<Dictionary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(code), Dictionary::getCode, code);
        Dictionary dictionary = dictionaryMapper.selectOne(queryWrapper);
        return Result.success(dictionary);
    }
}
