package com.nwu.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.user.model.dto.QueryPatientInfoDto;
import com.nwu.user.model.dto.UpdatePatientInfoDto;
import com.nwu.user.model.dto.patient.AddPatientDto;
import com.nwu.user.model.dto.patient.UpdatePatientDto;
import com.nwu.user.model.po.PatientInfo;

import java.util.List;

public interface IPatientInfoService extends IService<PatientInfo> {

    Result<PageResult<PatientInfo>> getPatientListByUserId(Long userId, PageParams pageParams);

    Result<?> deletePatientById(Long id);

    Result<?> getPatientInfoById(Long id);

    Result<?> updatePatientInfo(UpdatePatientInfoDto updatePatientInfoDto);

    Result<PageResult<PatientInfo>> queryPatientInfoList(PageParams pageParams, QueryPatientInfoDto queryPatientInfoDto);

    void addPatient(AddPatientDto addPatientDto);

    List<PatientInfo> queryAllPatient();

    void appUpdate(UpdatePatientDto updatePatientDto);

    void appDelete(Long patientId);
}
