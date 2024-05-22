package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.hospital.model.dto.admin.UpdateHospitalDto;
import com.nwu.hospital.model.po.HospitalInfo;

/**
 * <p>
 * 分院信息 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
public interface IHospitalInfoService extends IService<HospitalInfo> {

    void updateHospital(UpdateHospitalDto updateHospitalDto);
}
