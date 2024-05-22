package com.nwu.registration.api;


import com.nwu.base.constant.MessageConstant;
import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.InsertScheduleDto;
import com.nwu.registration.model.dto.QueryScheduleDto;
import com.nwu.registration.model.vo.ScheduleVo;
import com.nwu.registration.service.IScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "排班接口")
public class ScheduleController {
    @Autowired
    IScheduleService iScheduleService;

    @ApiOperation(value = "根据医院id,门诊id,日期返回排班列表")
    @PostMapping("")
    public Result<List<ScheduleVo>> getSchedules(@RequestBody QueryScheduleDto queryScheduleDto) {


        return iScheduleService.getSchedules(queryScheduleDto);
    }

    @ApiOperation(value = "新增排班")
    @PostMapping("/insert")
    public Result<?> insertSchedule(@RequestBody InsertScheduleDto insertScheduleDto) {
        return iScheduleService.insertSchedule(insertScheduleDto);
    }

    @ApiOperation(value = "根据id删除排班")
    @DeleteMapping("/delete")
    public Result<?> deleteSchedule(Long scheduleId) {
        return iScheduleService.removeById(scheduleId) ? Result.success() : Result.error(MessageConstant.DELETE_FAILED);
    }


}
