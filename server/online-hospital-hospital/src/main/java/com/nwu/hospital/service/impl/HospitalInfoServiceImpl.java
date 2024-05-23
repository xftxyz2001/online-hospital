package com.nwu.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.hospital.mapper.HospitalInfoMapper;
import com.nwu.hospital.model.dto.admin.UpdateHospitalDto;
import com.nwu.hospital.model.po.HospitalInfo;
import com.nwu.hospital.service.IHospitalInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo> implements IHospitalInfoService {

    @Autowired
    HospitalInfoMapper hospitalInfoMapper;

    @Override
    public void updateHospital(UpdateHospitalDto updateHospitalDto) {
        LambdaUpdateWrapper<HospitalInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(HospitalInfo::getId, updateHospitalDto.getId())
                .set(StringUtils.isNotEmpty(updateHospitalDto.getName()), HospitalInfo::getName, updateHospitalDto.getName())
                .set(StringUtils.isNotEmpty(updateHospitalDto.getPhone()), HospitalInfo::getPhone, updateHospitalDto.getPhone())
                .set(StringUtils.isNotEmpty(updateHospitalDto.getPicture()), HospitalInfo::getPicture, updateHospitalDto.getPicture())
                .set(StringUtils.isNotEmpty(updateHospitalDto.getAddress()), HospitalInfo::getAddress, updateHospitalDto.getAddress())
                .set(StringUtils.isNotEmpty(updateHospitalDto.getIntroduction()), HospitalInfo::getIntroduction, updateHospitalDto.getIntroduction())
                .set(HospitalInfo::getModifiedTime, LocalDateTime.now());
        hospitalInfoMapper.update(null, lambdaUpdateWrapper);
    }
}
