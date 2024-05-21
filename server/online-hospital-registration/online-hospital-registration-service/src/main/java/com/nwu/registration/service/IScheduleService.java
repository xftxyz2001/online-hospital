package com.nwu.registration.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.InsertScheduleDto;
import com.nwu.registration.model.dto.QueryDoctorScheduleDto;
import com.nwu.registration.model.dto.QueryScheduleDto;
import com.nwu.registration.model.po.Schedule;
import com.nwu.registration.model.vo.AppScheduleVo;
import com.nwu.registration.model.vo.ScheduleVo;

import java.util.List;

/**
 * <p>
 * 班版信息 服务类
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
public interface IScheduleService extends IService<Schedule> {

    Result<List<ScheduleVo>> getSchedules(QueryScheduleDto queryScheduleDto);

    Result insertSchedule(InsertScheduleDto insertScheduleDto);

    Result<List<AppScheduleVo>> getAppSchedules(QueryScheduleDto queryScheduleDto);

    Result<List<ScheduleVo>> getAppDoctorSchedules(QueryDoctorScheduleDto queryDoctorScheduleDto);
}
