package com.nwu.hospital.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.hospital.model.dto.admin.UpdateHospitalDto;
import com.nwu.hospital.model.po.HospitalInfo;


public interface IHospitalInfoService extends IService<HospitalInfo> {

    void updateHospital(UpdateHospitalDto updateHospitalDto);
}
