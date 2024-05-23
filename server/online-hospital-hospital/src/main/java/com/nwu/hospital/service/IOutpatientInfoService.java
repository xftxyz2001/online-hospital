package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.po.OutpatientInfo;

import java.util.List;


public interface IOutpatientInfoService extends IService<OutpatientInfo> {

    Result<List<OutpatientInfo>> queryOutpatientInfoByHospitalAndSpecialistId(Long hospitalId, Long specialistId);
}
