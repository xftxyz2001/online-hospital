package com.nwu.inquiry.api.app;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.nwu.base.model.Result;
import com.nwu.inquiry.model.dto.app.AddInquiryApplicationDto;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.app.AppQueryAllApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryOneApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.service.IInquiryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/4/2 13:07
 **/
@RestController
@RequestMapping("app/inquiry-application")
@Tag(name = "app问诊申请接口")
public class AppInquiryApplicationController {

    @Autowired
    IInquiryApplicationService iInquiryApplicationService;

    @Operation(summary = "新增问诊申请")
    @PostMapping("/add")
    public Result<?> addInquiryApplication(@RequestBody AddInquiryApplicationDto addInquiryApplicationDto) {
        iInquiryApplicationService.appAddInquiryApplication(addInquiryApplicationDto);
        return Result.success();
    }


    @Operation(summary = "查询等待中问诊申请详情")
    @GetMapping("/query-waiting-inquiry-application-detail")
    public Result<AppQueryWaitingInquiryApplicationDetailVo> queryWaitingInquiryApplicationDetail() {
        AppQueryWaitingInquiryApplicationDetailVo queryWaitingInquiryApplicationDetailVo = iInquiryApplicationService.appQueryWaitingInquiryApplicationDetail();
        return Result.success(queryWaitingInquiryApplicationDetailVo);
    }

    @Operation(summary = "查询所有的问诊")
    @GetMapping("/queryAll")
    public Result<List<AppQueryAllApplicationVo>> queryAll() {
        List<AppQueryAllApplicationVo> appQueryAllApplicationVos = iInquiryApplicationService.appQueryAll();
        return Result.success(appQueryAllApplicationVos);
    }

    @Operation(summary = "查询单个问诊")
    @GetMapping("/queryById")
    public Result<AppQueryOneApplicationVo> queryById(Long id) {
        AppQueryOneApplicationVo appQueryOneApplicationVo = iInquiryApplicationService.appQueryById(id);
        return Result.success(appQueryOneApplicationVo);
    }

    @Operation(summary = "取消问诊申请")
    @PutMapping("/cancel")
    public Result<?> cancel(Long id) {
        LambdaUpdateWrapper<InquiryApplication> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(InquiryApplication::getId, id)
                .eq(InquiryApplication::getStatus, 0)
                .set(InquiryApplication::getStatus, 3)
                .set(InquiryApplication::getCancelTime, LocalDateTime.now());
        boolean update = iInquiryApplicationService.update(lambdaUpdateWrapper);
        if (update) return Result.success();
        else return Result.error("操作失败，请刷新");

    }
}
