package com.nwu.registration.api.web;


import com.nwu.base.model.Result;
import com.nwu.registration.model.dto.AddReportDto;
import com.nwu.registration.model.po.ReportInfo;
import com.nwu.registration.service.IReportInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reportInfo/web")
@Tag(name = "web端检查报告接口")
public class WebReportInfoController {
    @Autowired
    IReportInfoService iReportInfoService;

    @Operation(summary = "添加报告")
    @PostMapping(value = "/insert")
    public Result insert(@RequestBody AddReportDto addReportDto) {
        ReportInfo reportInfo = new ReportInfo();
        BeanUtils.copyProperties(addReportDto, reportInfo);
        reportInfo.setSendTime(LocalDateTime.now());
        iReportInfoService.save(reportInfo);
        return Result.success();
    }
}
