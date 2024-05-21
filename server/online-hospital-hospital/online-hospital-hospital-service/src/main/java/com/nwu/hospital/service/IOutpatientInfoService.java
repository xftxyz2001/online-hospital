package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.po.OutpatientInfo;

import java.util.List;

/**
 * <p>
 * 门诊信息表 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
public interface IOutpatientInfoService extends IService<OutpatientInfo> {

    Result<List<OutpatientInfo>> queryOutpatientInfoByHospitalAndSpecialistId(Long hospitalId, Long specialistId);
}
