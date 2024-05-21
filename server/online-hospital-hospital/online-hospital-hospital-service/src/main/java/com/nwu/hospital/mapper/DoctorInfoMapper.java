package com.nwu.hospital.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.hospital.model.po.DoctorInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 医生信息表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-03-07
 */
@Mapper
public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {

}
