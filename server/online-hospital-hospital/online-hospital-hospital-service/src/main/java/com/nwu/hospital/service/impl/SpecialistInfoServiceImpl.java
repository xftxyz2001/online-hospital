package com.nwu.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.model.Result;
import com.nwu.hospital.mapper.HospitalSpecialistMapper;
import com.nwu.hospital.mapper.OutpatientInfoMapper;
import com.nwu.hospital.mapper.SpecialistInfoMapper;
import com.nwu.hospital.model.po.OutpatientInfo;
import com.nwu.hospital.model.po.SpecialistInfo;
import com.nwu.hospital.model.vo.AppOutpatientTreeVo;
import com.nwu.hospital.model.vo.AppSpecialistTreeVo;
import com.nwu.hospital.model.vo.OutpatientTreeVo;
import com.nwu.hospital.model.vo.SpecialistTreeVo;
import com.nwu.hospital.service.ISpecialistInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 专科 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Service
public class SpecialistInfoServiceImpl extends ServiceImpl<SpecialistInfoMapper, SpecialistInfo> implements ISpecialistInfoService {

    @Autowired
    HospitalSpecialistMapper hospitalSpecialistMapper;
    @Autowired
    SpecialistInfoMapper specialistInfoMapper;
    @Autowired
    OutpatientInfoMapper outpatientInfoMapper;

    @Override
    public Result<List<SpecialistInfo>> getSpecialistByHospitalId(Long hospitalId) {
        LambdaQueryWrapper<SpecialistInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpecialistInfo::getHospitalId, hospitalId);
        List<SpecialistInfo> specialistInfos = specialistInfoMapper.selectList(lambdaQueryWrapper);
        return Result.success(specialistInfos);
    }

    @Override
    public Result<List<SpecialistTreeVo>> querySpecialistTree(Long hospitalId) {
        LambdaQueryWrapper<SpecialistInfo> specialistInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        specialistInfoLambdaQueryWrapper.eq(SpecialistInfo::getHospitalId, hospitalId);
        //该医院的所有专科信息
        List<SpecialistInfo> specialistInfos = specialistInfoMapper.selectList(specialistInfoLambdaQueryWrapper);

        List<SpecialistTreeVo> specialistTreeVos = new ArrayList<>();
        for (int i = 0; i < specialistInfos.size(); i++) {
            SpecialistTreeVo specialistTreeVo = new SpecialistTreeVo();
            specialistTreeVo.setLabel(specialistInfos.get(i).getName());
            LambdaQueryWrapper<OutpatientInfo> outpatientInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
            outpatientInfoLambdaQueryWrapper.eq(OutpatientInfo::getSpecialistId, specialistInfos.get(i).getId());
            List<OutpatientInfo> outpatientInfos = outpatientInfoMapper.selectList(outpatientInfoLambdaQueryWrapper);
            List<OutpatientTreeVo> outpatientTreeVos = new ArrayList<>();
            outpatientInfos.forEach(item -> {
                OutpatientTreeVo outpatientTreeVo = new OutpatientTreeVo();
                outpatientTreeVo.setLabel(item.getName());
                outpatientTreeVo.setValue(item.getId());
                outpatientTreeVos.add(outpatientTreeVo);
            });
            specialistTreeVo.setChildren(outpatientTreeVos);
            specialistTreeVos.add(specialistTreeVo);
        }
        return Result.success(specialistTreeVos);
    }

    @Override
    public Result<List<AppSpecialistTreeVo>> queryAppSpecialistTree(Long hospitalId) {
        LambdaQueryWrapper<SpecialistInfo> specialistInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        specialistInfoLambdaQueryWrapper.eq(SpecialistInfo::getHospitalId, hospitalId);
        //该医院的所有专科信息
        List<SpecialistInfo> specialistInfos = specialistInfoMapper.selectList(specialistInfoLambdaQueryWrapper);

        List<AppSpecialistTreeVo> appSpecialistTreeVos = new ArrayList<>();
        for (int i = 0; i < specialistInfos.size(); i++) {
            AppSpecialistTreeVo appSpecialistTreeVo = new AppSpecialistTreeVo();
            appSpecialistTreeVo.setText(specialistInfos.get(i).getName());
            LambdaQueryWrapper<OutpatientInfo> outpatientInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
            outpatientInfoLambdaQueryWrapper.eq(OutpatientInfo::getSpecialistId, specialistInfos.get(i).getId());
            List<OutpatientInfo> outpatientInfos = outpatientInfoMapper.selectList(outpatientInfoLambdaQueryWrapper);
            List<AppOutpatientTreeVo> appOutpatientTreeVos = new ArrayList<>();
            outpatientInfos.forEach(item -> {
                AppOutpatientTreeVo appOutpatientTreeVo = new AppOutpatientTreeVo();
                appOutpatientTreeVo.setText(item.getName());
                appOutpatientTreeVo.setId(item.getId());
                appOutpatientTreeVos.add(appOutpatientTreeVo);
            });
            appSpecialistTreeVo.setChildren(appOutpatientTreeVos);
            appSpecialistTreeVos.add(appSpecialistTreeVo);
        }
        return Result.success(appSpecialistTreeVos);
    }
}
