package com.nwu.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.user.model.po.PatientInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 就诊人表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-03-06
 */
@Mapper
public interface PatientInfoMapper extends BaseMapper<PatientInfo> {

}
