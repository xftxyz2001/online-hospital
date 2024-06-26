package com.nwu.registration.api.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nwu.base.context.BaseContext;
import com.nwu.base.model.Result;
import com.nwu.registration.model.po.ReportInfo;
import com.nwu.registration.model.vo.AppReportInfoVo;
import com.nwu.registration.service.IReportInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reportInfo/app")
@Tag(name = "app端检查报告接口")
public class AppReportInfoController {
    @Autowired
    IReportInfoService iReportInfoService;

    @Operation(summary = "请求所有报告接口")
    @GetMapping("/queryAll")
    public Result<List<AppReportInfoVo>> queryReportList() {
        LambdaQueryWrapper<ReportInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ReportInfo::getUserId, BaseContext.getUserIdentity().getId())
                .orderByDesc(ReportInfo::getSendTime);
        List<ReportInfo> reportInfos = iReportInfoService.list(lambdaQueryWrapper);
        List<AppReportInfoVo> appReportInfoVos = new ArrayList<>();
        for (ReportInfo reportInfo : reportInfos) {
            AppReportInfoVo appReportInfoVo = new AppReportInfoVo();
            BeanUtils.copyProperties(reportInfo, appReportInfoVo);
            appReportInfoVos.add(appReportInfoVo);
        }
        return Result.success(appReportInfoVos);
    }

}
