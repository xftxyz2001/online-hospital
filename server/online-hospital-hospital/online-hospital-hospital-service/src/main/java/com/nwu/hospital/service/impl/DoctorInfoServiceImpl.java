package com.nwu.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.constant.UserConstant;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.hospital.mapper.HospitalInfoMapper;
import com.nwu.hospital.mapper.OutpatientInfoMapper;
import com.nwu.hospital.model.dto.admin.QueryDoctorInfoPageDto;
import com.nwu.hospital.model.dto.app.QueryInquiryDoctorListDto;
import com.nwu.hospital.model.dto.web.DoctorLoginDto;
import com.nwu.hospital.model.po.DoctorInfo;
import com.nwu.hospital.mapper.DoctorInfoMapper;
import com.nwu.hospital.model.po.OutpatientInfo;
import com.nwu.hospital.model.vo.AppDoctorInfoVo;
import com.nwu.hospital.model.vo.DoctorInfoPageVo;
import com.nwu.hospital.service.IDoctorInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 医生信息表 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Service
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements IDoctorInfoService {
    @Autowired
    DoctorInfoMapper doctorInfoMapper;
    //    @Autowired
//    RedisTemplate redisTemplate;
    @Autowired
    HospitalInfoMapper hospitalInfoMapper;

    @Autowired
    OutpatientInfoMapper outpatientInfoMapper;

    @Override
    public Result<List<DoctorInfo>> queryByHospitalAndOutpatientId(Long hospitalId, Long outpatientId) {

//        Set members = setOperations.members("hospital:doctor:hAndO:" + hospitalId + ":" + outpatientId);
//
//
//        List<DoctorInfo> redisDoctorInfos=new ArrayList<>(members);
//        if(members.size()>0) {
//            return Result.success(redisDoctorInfos);
//        }
//        else {
        LambdaQueryWrapper<DoctorInfo> doctorInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        doctorInfoLambdaQueryWrapper.eq(DoctorInfo::getHospitalId, hospitalId);
        doctorInfoLambdaQueryWrapper.eq(DoctorInfo::getOutpatientId, outpatientId);
        List<DoctorInfo> doctorInfos = doctorInfoMapper.selectList(doctorInfoLambdaQueryWrapper);
//           setOperations.add("hospital:doctor:hAndO:" + hospitalId + ":" + outpatientId, doctorInfos.toArray());

        return Result.success(doctorInfos);
//        }
    }


    //app


    @Override
    public List<DoctorInfo> appQueryInquiryDoctorList(QueryInquiryDoctorListDto queryInquiryDoctorListDto) {
        LambdaQueryWrapper<DoctorInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DoctorInfo::getHospitalId, queryInquiryDoctorListDto.getHospitalId());
        lambdaQueryWrapper.eq(DoctorInfo::getOutpatientId, queryInquiryDoctorListDto.getOutpatientId());
        lambdaQueryWrapper.eq(DoctorInfo::getIsInquiry, 1);
        lambdaQueryWrapper.eq(DoctorInfo::getIsOutCall, 1);
        List<DoctorInfo> doctorInfos = doctorInfoMapper.selectList(lambdaQueryWrapper);
        return doctorInfos;
    }

    @Override
    public Result<PageResult<DoctorInfoPageVo>> queryPageDoctorList(PageParams pageParams, QueryDoctorInfoPageDto queryDoctorInfoPageDto) {
        Page<DoctorInfo> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        LambdaQueryWrapper<DoctorInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryDoctorInfoPageDto.getName()), DoctorInfo::getName, queryDoctorInfoPageDto.getName())
                .like(StringUtils.isNotEmpty(queryDoctorInfoPageDto.getProfessionalTitle()), DoctorInfo::getProfessionalTitle, queryDoctorInfoPageDto.getProfessionalTitle())
                .eq(queryDoctorInfoPageDto.getGender() != null, DoctorInfo::getGender, queryDoctorInfoPageDto.getGender())
                .eq(queryDoctorInfoPageDto.getHospitalId() != null, DoctorInfo::getHospitalId, queryDoctorInfoPageDto.getHospitalId())
                .eq(queryDoctorInfoPageDto.getOutpatientId() != null, DoctorInfo::getOutpatientId, queryDoctorInfoPageDto.getOutpatientId())
                .like(StringUtils.isNotEmpty(queryDoctorInfoPageDto.getProfessionalTitle()), DoctorInfo::getProfessionalTitle, queryDoctorInfoPageDto.getProfessionalTitle())
                .eq(queryDoctorInfoPageDto.getIsInquiry() != null, DoctorInfo::getIsInquiry, queryDoctorInfoPageDto.getIsInquiry());

        Page<DoctorInfo> doctorInfoPage = doctorInfoMapper.selectPage(page, lambdaQueryWrapper);
        List<DoctorInfo> doctorInfoList = doctorInfoPage.getRecords();
        Long counts = doctorInfoPage.getTotal();
        List<DoctorInfoPageVo> doctorInfoPageVos = new ArrayList<>();
        for (DoctorInfo doctorInfo : doctorInfoList) {
            DoctorInfoPageVo doctorInfoPageVo = new DoctorInfoPageVo();
            BeanUtils.copyProperties(doctorInfo, doctorInfoPageVo);
            doctorInfoPageVos.add(doctorInfoPageVo);
        }
        PageResult<DoctorInfoPageVo> doctorInfoPageResult = new PageResult<>(doctorInfoPageVos, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(doctorInfoPageResult);
    }

    @Override
    public AppDoctorInfoVo appQueryById(Long doctorId) {
        DoctorInfo doctorInfo = getById(doctorId);
        AppDoctorInfoVo appDoctorInfoVo = new AppDoctorInfoVo();
        BeanUtils.copyProperties(doctorInfo, appDoctorInfoVo);
        appDoctorInfoVo.setHospitalName(hospitalInfoMapper.selectById(doctorInfo.getHospitalId()).getName());
        appDoctorInfoVo.setOutpatientName(outpatientInfoMapper.selectById(doctorInfo.getOutpatientId()).getName());
        return appDoctorInfoVo;
    }

    @Override
    public DoctorInfo login(DoctorLoginDto doctorLoginDto) {
        LambdaQueryWrapper<DoctorInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DoctorInfo::getUsername, doctorLoginDto.getUsername());
        lambdaQueryWrapper.eq(DoctorInfo::getPassword, doctorLoginDto.getPassword());
        DoctorInfo doctorInfo = doctorInfoMapper.selectOne(lambdaQueryWrapper);

        return doctorInfo;
    }
}
