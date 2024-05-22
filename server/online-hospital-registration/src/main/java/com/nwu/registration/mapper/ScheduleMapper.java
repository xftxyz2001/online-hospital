package com.nwu.registration.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nwu.registration.model.po.Schedule;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 排班信息 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

}
