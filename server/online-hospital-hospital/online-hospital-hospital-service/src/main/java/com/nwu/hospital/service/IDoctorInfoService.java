package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.dto.admin.QueryDoctorInfoPageDto;
import com.nwu.hospital.model.dto.app.QueryInquiryDoctorListDto;
import com.nwu.hospital.model.dto.web.DoctorLoginDto;
import com.nwu.hospital.model.po.DoctorInfo;
import com.nwu.hospital.model.vo.AppDoctorInfoVo;
import com.nwu.hospital.model.vo.DoctorInfoPageVo;

import java.util.List;

/**
 * <p>
 * 医生信息表 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
public interface IDoctorInfoService extends IService<DoctorInfo> {

    Result<List<DoctorInfo>> queryByHospitalAndOutpatientId(Long hospitalId, Long outpatientId);

    List<DoctorInfo> appQueryInquiryDoctorList(QueryInquiryDoctorListDto queryInquiryDoctorListDto);

    Result<PageResult<DoctorInfoPageVo>> queryPageDoctorList(PageParams pageParams, QueryDoctorInfoPageDto queryDoctorInfoPageDto);

    AppDoctorInfoVo appQueryById(Long doctorId);

    DoctorInfo login(DoctorLoginDto doctorLoginDto);
}
