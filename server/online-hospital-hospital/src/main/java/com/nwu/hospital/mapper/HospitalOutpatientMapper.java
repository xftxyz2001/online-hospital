package com.nwu.hospital.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.hospital.model.po.HospitalOutpatient;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 医院-门诊对应表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Mapper
public interface HospitalOutpatientMapper extends BaseMapper<HospitalOutpatient> {

}
