package com.nwu.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.constant.ErrorMessages;
import com.nwu.base.context.BaseContext;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.mapper.PatientInfoMapper;
import com.nwu.user.model.dto.QueryPatientInfoDto;
import com.nwu.user.model.dto.UpdatePatientInfoDto;
import com.nwu.user.model.dto.patient.AddPatientDto;
import com.nwu.user.model.dto.patient.UpdatePatientDto;
import com.nwu.user.model.po.PatientInfo;
import com.nwu.user.service.IPatientInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientInfoServiceImpl extends ServiceImpl<PatientInfoMapper, PatientInfo> implements IPatientInfoService {
    @Autowired
    PatientInfoMapper patientInfoMapper;

    @Override
    public Result<PageResult<PatientInfo>> getPatientListByUserId(Long userId, PageParams pageParams) {
        Page<PatientInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        LambdaQueryWrapper<PatientInfo> patientInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        patientInfoLambdaQueryWrapper.eq(PatientInfo::getUserId, userId);
        patientInfoLambdaQueryWrapper.eq(PatientInfo::getIsDeleted, PatientInfo.NOT_DELETED);
        Page<PatientInfo> patientInfoPage = patientInfoMapper.selectPage(page, patientInfoLambdaQueryWrapper);
        List<PatientInfo> patientInfoList = patientInfoPage.getRecords();
        Long counts = patientInfoPage.getTotal();
        PageResult<PatientInfo> patientInfoPageResult = new PageResult<>(patientInfoList, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(patientInfoPageResult);
    }

    @Override
    public Result<?> deletePatientById(Long id) {
        LambdaUpdateWrapper<PatientInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(PatientInfo::getId, id);
        lambdaUpdateWrapper.set(PatientInfo::getIsDeleted, PatientInfo.IS_DELETED);
        patientInfoMapper.update(null, lambdaUpdateWrapper);
        return Result.success();
    }

    @Override
    public Result<?> getPatientInfoById(Long id) {
        PatientInfo patientInfo = patientInfoMapper.selectById(id);
        if (patientInfo == null) {
            return Result.error(ErrorMessages.QUERY_FAILED);
        } else {
            return Result.success(patientInfo);
        }
    }

    @Override
    public Result<?> updatePatientInfo(UpdatePatientInfoDto updatePatientInfoDto) {
        updatePatientInfoDto.setUpdateTime(LocalDateTime.now());
        PatientInfo patientInfo = new PatientInfo();
        BeanUtils.copyProperties(updatePatientInfoDto, patientInfo);
        patientInfoMapper.updateById(patientInfo);
        return Result.success();
    }

    @Override
    public Result<PageResult<PatientInfo>> queryPatientInfoList(PageParams pageParams, QueryPatientInfoDto queryPatientInfoDto) {

        Page<PatientInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        LambdaQueryWrapper<PatientInfo> patientInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        patientInfoLambdaQueryWrapper.like(StringUtils.isNotEmpty(queryPatientInfoDto.getName()), PatientInfo::getName, queryPatientInfoDto.getName())
                .like(StringUtils.isNotEmpty(queryPatientInfoDto.getPhone()), PatientInfo::getPhone, queryPatientInfoDto.getPhone())
                .eq(StringUtils.isNotEmpty(queryPatientInfoDto.getSex()), PatientInfo::getSex, queryPatientInfoDto.getSex())
                .eq(PatientInfo::getIsDeleted, PatientInfo.NOT_DELETED);
        Page<PatientInfo> patientInfoPage = patientInfoMapper.selectPage(page, patientInfoLambdaQueryWrapper);
        List<PatientInfo> patientInfoList = patientInfoPage.getRecords();
        Long counts = patientInfoPage.getTotal();
        PageResult<PatientInfo> patientInfoPageResult = new PageResult<>(patientInfoList, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(patientInfoPageResult);
    }


    //app就诊人接口
    @Override
    public void addPatient(AddPatientDto addPatientDto) {
        PatientInfo patientInfo = new PatientInfo();
        BeanUtils.copyProperties(addPatientDto, patientInfo);
        patientInfo.setUserId(BaseContext.getUserIdentity().getId());
        patientInfo.setCreateTime(LocalDateTime.now());
        patientInfo.setStatus(1);
        patientInfo.setIsDeleted(0);
        patientInfo.setUpdateTime(LocalDateTime.now());
        patientInfo.setCardNo(System.currentTimeMillis() + BaseContext.getUserIdentity().getId().toString());
        patientInfoMapper.insert(patientInfo);
    }

    @Override
    public List<PatientInfo> queryAllPatient() {
        LambdaQueryWrapper<PatientInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(PatientInfo::getUserId, BaseContext.getUserIdentity().getId())
                .eq(PatientInfo::getIsDeleted, 0);
        return patientInfoMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void appUpdate(UpdatePatientDto updatePatientDto) {
        LambdaUpdateWrapper<PatientInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(PatientInfo::getId, updatePatientDto.getId())
                .set(StringUtils.isNotEmpty(updatePatientDto.getAreaCode()), PatientInfo::getAreaCode, updatePatientDto.getAreaCode())
                .set(StringUtils.isNotEmpty(updatePatientDto.getProvinceName()), PatientInfo::getProvinceName, updatePatientDto.getProvinceName())
                .set(StringUtils.isNotEmpty(updatePatientDto.getCityName()), PatientInfo::getCityName, updatePatientDto.getCityName())
                .set(StringUtils.isNotEmpty(updatePatientDto.getDistrictName()), PatientInfo::getDistrictName, updatePatientDto.getDistrictName())
                .set(StringUtils.isNotEmpty(updatePatientDto.getAddress()), PatientInfo::getAddress, updatePatientDto.getAddress())
                .set(StringUtils.isNotEmpty(updatePatientDto.getPhone()), PatientInfo::getPhone, updatePatientDto.getPhone());
        patientInfoMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public void appDelete(Long patientId) {
        LambdaUpdateWrapper<PatientInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(PatientInfo::getId, patientId)
                .set(PatientInfo::getIsDeleted, 1);
        patientInfoMapper.update(null, lambdaUpdateWrapper);
    }
}
