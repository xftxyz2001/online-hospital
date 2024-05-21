package com.nwu.registration.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.model.Result;


import com.nwu.registration.clients.HospitalClient;
import com.nwu.registration.mapper.ScheduleMapper;
import com.nwu.registration.model.clientspo.DoctorInfo;
import com.nwu.registration.model.dto.InsertScheduleDto;
import com.nwu.registration.model.dto.QueryDoctorScheduleDto;
import com.nwu.registration.model.dto.QueryScheduleDto;
import com.nwu.registration.model.po.Schedule;
import com.nwu.registration.model.vo.AppScheduleVo;
import com.nwu.registration.model.vo.ScheduleVo;
import com.nwu.registration.service.IScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 排班信息 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Autowired
    HospitalClient hospitalClient;

    @Override
    public Result<List<ScheduleVo>> getSchedules(QueryScheduleDto queryScheduleDto) {
        List<ScheduleVo> scheduleVos = new ArrayList<>();
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getHospitalId, queryScheduleDto.getHospitalId())
                .eq(Schedule::getOutpatientId, queryScheduleDto.getOutpatientId())
                .eq(Schedule::getWorkDate, queryScheduleDto.getWorkDate());
        List<Schedule> schedules = scheduleMapper.selectList(scheduleLambdaQueryWrapper);
        for (int i = 0; i < schedules.size(); i++) {
            ScheduleVo scheduleVo = new ScheduleVo();
            BeanUtils.copyProperties(schedules.get(i), scheduleVo);
            Result doctorInfoResult = hospitalClient.queryDoctorById(schedules.get(i).getDoctorId());
            DoctorInfo doctorInfo = (DoctorInfo) doctorInfoResult.getData();
            scheduleVo.setDoctorName(doctorInfo.getName());
            scheduleVo.setDoctorIntroduce(doctorInfo.getIntroduce());
            scheduleVo.setProfessionalTitle(doctorInfo.getProfessionalTitle());
            scheduleVos.add(scheduleVo);
        }
        return Result.success(scheduleVos);
    }

    @Override
    public Result insertSchedule(InsertScheduleDto insertScheduleDto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(insertScheduleDto, schedule);
        schedule.setCreateTime(LocalDateTime.now());
        schedule.setStatus(1);
        schedule.setAvailableNumber(insertScheduleDto.getReservedNumber());
        scheduleMapper.insert(schedule);
        return Result.success();
    }

    @Override
    public Result<List<AppScheduleVo>> getAppSchedules(QueryScheduleDto queryScheduleDto) {
        List<ScheduleVo> scheduleVos = new ArrayList<>();
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getHospitalId, queryScheduleDto.getHospitalId())
                .eq(Schedule::getOutpatientId, queryScheduleDto.getOutpatientId())
                .eq(Schedule::getWorkDate, queryScheduleDto.getWorkDate());
        List<Schedule> schedules = scheduleMapper.selectList(scheduleLambdaQueryWrapper);
        for (int i = 0; i < schedules.size(); i++) {
            ScheduleVo scheduleVo = new ScheduleVo();
            BeanUtils.copyProperties(schedules.get(i), scheduleVo);
            Result doctorInfoResult = hospitalClient.queryDoctorById(schedules.get(i).getDoctorId());
            DoctorInfo doctorInfo = (DoctorInfo) doctorInfoResult.getData();
            scheduleVo.setDoctorName(doctorInfo.getName());
            scheduleVo.setDoctorIntroduce(doctorInfo.getIntroduce());
            scheduleVo.setProfessionalTitle(doctorInfo.getProfessionalTitle());
            scheduleVo.setPicture(doctorInfo.getPicture());
            scheduleVos.add(scheduleVo);
        }
        List<ScheduleVo> morningScheduleVos = new ArrayList<>();
        List<ScheduleVo> afternoonScheduleVos = new ArrayList<>();
        HashMap<Long, AppScheduleVo> morningHashMap = new HashMap<>();
        HashMap<Long, AppScheduleVo> afternoonHashMap = new HashMap<>();
        for (int i = 0; i < scheduleVos.size(); i++) {
            if (scheduleVos.get(i).getWorkHalf() == 0) morningScheduleVos.add(scheduleVos.get(i));
            else afternoonScheduleVos.add(scheduleVos.get(i));
        }
        for (int i = 0; i < morningScheduleVos.size(); i++) {
            ScheduleVo scheduleVo = morningScheduleVos.get(i);
            if (!morningHashMap.containsKey(scheduleVo.getDoctorId())) {
                AppScheduleVo appScheduleVo = new AppScheduleVo();
                BeanUtils.copyProperties(scheduleVo, appScheduleVo);
                appScheduleVo.setHasReservedNumber(scheduleVo.getReservedNumber() - scheduleVo.getAvailableNumber());
                morningHashMap.put(scheduleVo.getDoctorId(), appScheduleVo);
            } else {
                AppScheduleVo appScheduleVo = morningHashMap.get(scheduleVo.getDoctorId());
                appScheduleVo.setAvailableNumber(appScheduleVo.getAvailableNumber() + scheduleVo.getAvailableNumber());
                appScheduleVo.setHasReservedNumber(appScheduleVo.getHasReservedNumber() + scheduleVo.getReservedNumber() - scheduleVo.getAvailableNumber());
                morningHashMap.replace(scheduleVo.getDoctorId(), appScheduleVo);
            }
        }
        for (int i = 0; i < afternoonScheduleVos.size(); i++) {
            ScheduleVo scheduleVo = afternoonScheduleVos.get(i);
            if (!afternoonHashMap.containsKey(scheduleVo.getDoctorId())) {
                AppScheduleVo appScheduleVo = new AppScheduleVo();
                BeanUtils.copyProperties(scheduleVo, appScheduleVo);
                appScheduleVo.setHasReservedNumber(scheduleVo.getReservedNumber() - scheduleVo.getAvailableNumber());
                afternoonHashMap.put(scheduleVo.getDoctorId(), appScheduleVo);
            } else {
                AppScheduleVo appScheduleVo = afternoonHashMap.get(scheduleVo.getDoctorId());
                appScheduleVo.setAvailableNumber(appScheduleVo.getAvailableNumber() + scheduleVo.getAvailableNumber());
                appScheduleVo.setHasReservedNumber(appScheduleVo.getHasReservedNumber() + scheduleVo.getReservedNumber() - scheduleVo.getAvailableNumber());
                afternoonHashMap.replace(scheduleVo.getDoctorId(), appScheduleVo);
            }
        }
        List<AppScheduleVo> appScheduleVos = new ArrayList<>();
        appScheduleVos.addAll(morningHashMap.values());
        appScheduleVos.addAll(afternoonHashMap.values());
        return Result.success(appScheduleVos);
    }

    @Override
    public Result<List<ScheduleVo>> getAppDoctorSchedules(QueryDoctorScheduleDto queryDoctorScheduleDto) {
        List<ScheduleVo> scheduleVos = new ArrayList<>();
        LambdaQueryWrapper<Schedule> scheduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scheduleLambdaQueryWrapper.eq(Schedule::getHospitalId, queryDoctorScheduleDto.getHospitalId())
                .eq(Schedule::getOutpatientId, queryDoctorScheduleDto.getOutpatientId())
                .eq(Schedule::getWorkDate, queryDoctorScheduleDto.getWorkDate())
                .eq(Schedule::getDoctorId, queryDoctorScheduleDto.getDoctorId())
                .eq(Schedule::getWorkHalf, queryDoctorScheduleDto.getWorkHalf());

        List<Schedule> schedules = scheduleMapper.selectList(scheduleLambdaQueryWrapper);
        for (int i = 0; i < schedules.size(); i++) {
            ScheduleVo scheduleVo = new ScheduleVo();
            BeanUtils.copyProperties(schedules.get(i), scheduleVo);

            scheduleVos.add(scheduleVo);
        }
        return Result.success(scheduleVos);
    }
}
