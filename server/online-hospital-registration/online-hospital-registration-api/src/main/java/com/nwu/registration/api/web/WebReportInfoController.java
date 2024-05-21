package com.nwu.registration.api.web;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.AddReportDto;
import com.nwu.registration.model.po.ReportInfo;
import com.nwu.registration.service.IReportInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reportInfo/web")
@Api(tags = "web端检查报告接口")
public class WebReportInfoController {
    @Autowired
    IReportInfoService iReportInfoService;

    @ApiOperation(value = "添加报告")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody AddReportDto addReportDto) {
        ReportInfo reportInfo = new ReportInfo();
        BeanUtils.copyProperties(addReportDto, reportInfo);
        reportInfo.setSendTime(LocalDateTime.now());
        iReportInfoService.save(reportInfo);
        return Result.success();
    }
}
