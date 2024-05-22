package com.nwu.registration.api.app;

import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.QueryDoctorScheduleDto;
import com.nwu.registration.model.dto.QueryScheduleDto;
import com.nwu.registration.model.vo.AppScheduleVo;
import com.nwu.registration.model.vo.ScheduleVo;
import com.nwu.registration.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/24 16:10
 **/
@RestController
@RequestMapping("/schedule/app")
@Tag(name = "app排班接口")
public class AppScheduleController {
    @Autowired
    IScheduleService iScheduleService;

    @Operation(summary = "根据医院id,门诊id,日期返回App排班列表")
    @PostMapping("")
    public Result<List<AppScheduleVo>> getAppSchedules(@RequestBody QueryScheduleDto queryScheduleDto) {
        return iScheduleService.getAppSchedules(queryScheduleDto);
    }

    @Operation(summary = "返回App具体医生的半天排版列表")
    @PostMapping("/doctor")
    public Result<List<ScheduleVo>> getAppDoctorSchedules(@RequestBody QueryDoctorScheduleDto queryDoctorScheduleDto) {
        return iScheduleService.getAppDoctorSchedules(queryDoctorScheduleDto);
    }
}
