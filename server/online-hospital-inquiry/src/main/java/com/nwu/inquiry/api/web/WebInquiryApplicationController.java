package com.nwu.inquiry.api.web;

import com.nwu.base.model.Result;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.web.WebQueryAllFinishedInquiryVo;
import com.nwu.inquiry.model.vo.web.WebQueryOneFinishedInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationListVo;
import com.nwu.inquiry.service.IInquiryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// web问诊申请接口
@RestController
@RequestMapping("web/inquiry-application")
@Tag(name = "web问诊申请接口")
public class WebInquiryApplicationController {
    @Autowired
    IInquiryApplicationService iInquiryApplicationService;

    @Operation(summary = "查询等待中问诊申请列表")
    @GetMapping("/query-waiting-list")
    public Result<List<WebQueryWaitingInquiryApplicationListVo>> queryWaitingInquiryApplicationList() {
        List<InquiryApplication> inquiryApplications = iInquiryApplicationService.webQueryWaitingInquiryApplicationList();
        List<WebQueryWaitingInquiryApplicationListVo> webQueryWaitingInquiryApplicationListVos = new ArrayList<>();
        for (InquiryApplication inquiryApplication : inquiryApplications) {
            WebQueryWaitingInquiryApplicationListVo webQueryWaitingInquiryApplicationListVo = new WebQueryWaitingInquiryApplicationListVo();
            BeanUtils.copyProperties(inquiryApplication, webQueryWaitingInquiryApplicationListVo);
            webQueryWaitingInquiryApplicationListVos.add(webQueryWaitingInquiryApplicationListVo);
        }
        return Result.success(webQueryWaitingInquiryApplicationListVos);
    }

    @Operation(summary = "查询等待中问诊申请详情")
    @GetMapping("/query-waiting-detail")
    public Result<WebQueryWaitingInquiryApplicationDetailVo> queryWaitingInquiryApplicationDetail(Long id) {
        WebQueryWaitingInquiryApplicationDetailVo webQueryWaitingInquiryApplicationDetailVo = iInquiryApplicationService.webQueryWaitingInquiryApplicationDetail(id);
        return Result.success(webQueryWaitingInquiryApplicationDetailVo);
    }


    @Operation(summary = "接诊")
    @GetMapping("/reception")
    public Result<?> reception(Long id) {
        iInquiryApplicationService.changeWaitingToDoing(id);
        return Result.success();
    }

    @Operation(summary = "查询所有已完成问诊")
    @GetMapping("/allFinished")
    public Result<List<WebQueryAllFinishedInquiryVo>> allFinished() {
        List<WebQueryAllFinishedInquiryVo> webQueryAllFinishedInquiryVos = iInquiryApplicationService.webQueryFinishedInquiryApplications();
        return Result.success(webQueryAllFinishedInquiryVos);
    }

    @Operation(summary = "查询单个已完成问诊详情")
    @GetMapping("/queryFinishedById")
    public Result<WebQueryOneFinishedInquiryApplicationDetailVo> queryFinishedById(Long id) {
        WebQueryOneFinishedInquiryApplicationDetailVo webQueryOneFinishedInquiryApplicationDetailVo = iInquiryApplicationService.webQueryFinishedById(id);
        return Result.success(webQueryOneFinishedInquiryApplicationDetailVo);
    }


}
