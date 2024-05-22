package com.nwu.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.model.Result;
import com.nwu.hospital.mapper.OutpatientInfoMapper;
import com.nwu.hospital.model.po.OutpatientInfo;
import com.nwu.hospital.service.IOutpatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门诊信息表 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Service
public class OutpatientInfoServiceImpl extends ServiceImpl<OutpatientInfoMapper, OutpatientInfo> implements IOutpatientInfoService {

    @Autowired
    OutpatientInfoMapper outpatientInfoMapper;

    @Override
    public Result<List<OutpatientInfo>> queryOutpatientInfoByHospitalAndSpecialistId(Long hospitalId, Long specialistId) {
        LambdaQueryWrapper<OutpatientInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OutpatientInfo::getSpecialistId, specialistId)
                .eq(OutpatientInfo::getHospitalId, hospitalId);
        List<OutpatientInfo> outpatientInfos = outpatientInfoMapper.selectList(lambdaQueryWrapper);
        return Result.success(outpatientInfos);
    }
}
