package com.nwu.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.hospital.mapper.HospitalOutpatientMapper;
import com.nwu.hospital.model.po.HospitalOutpatient;
import com.nwu.hospital.service.IHospitalOutpatientService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医院-门诊对应表 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Service
public class HospitalOutpatientServiceImpl extends ServiceImpl<HospitalOutpatientMapper, HospitalOutpatient> implements IHospitalOutpatientService {

}
