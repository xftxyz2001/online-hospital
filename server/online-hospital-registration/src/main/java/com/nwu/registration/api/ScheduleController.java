package com.nwu.registration.api;


import com.nwu.base.constant.ErrorMessages;
import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.InsertScheduleDto;
import com.nwu.registration.model.dto.QueryScheduleDto;
import com.nwu.registration.model.vo.ScheduleVo;
import com.nwu.registration.service.IScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 排班信息 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/schedule")
@Tag(name = "排班接口")
public class ScheduleController {
    @Autowired
    IScheduleService iScheduleService;

    @Operation(summary = "根据医院id,门诊id,日期返回排班列表")
    @PostMapping("")
    public Result<List<ScheduleVo>> getSchedules(@RequestBody QueryScheduleDto queryScheduleDto) {


        return iScheduleService.getSchedules(queryScheduleDto);
    }

    @Operation(summary = "新增排班")
    @PostMapping("/insert")
    public Result<?> insertSchedule(@RequestBody InsertScheduleDto insertScheduleDto) {
        return iScheduleService.insertSchedule(insertScheduleDto);
    }

    @Operation(summary = "根据id删除排班")
    @DeleteMapping("/delete")
    public Result<?> deleteSchedule(Long scheduleId) {
        return iScheduleService.removeById(scheduleId) ? Result.success() : Result.error(ErrorMessages.DELETE_FAILED);
    }


}
