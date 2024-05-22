package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.hospital.model.po.SpecialistInfo;
import com.nwu.hospital.model.vo.AppSpecialistTreeVo;
import com.nwu.hospital.model.vo.SpecialistTreeVo;

import java.util.List;

/**
 * <p>
 * 专科 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
public interface ISpecialistInfoService extends IService<SpecialistInfo> {

    Result<List<SpecialistInfo>> getSpecialistByHospitalId(Long id);

    Result<List<SpecialistTreeVo>> querySpecialistTree(Long hospitalId);

    Result<List<AppSpecialistTreeVo>> queryAppSpecialistTree(Long hospitalId);
}
