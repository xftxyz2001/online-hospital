package com.nwu.inquiry.api.web;

import com.nwu.base.model.Result;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.web.WebQueryAllFinishedInquiryVo;
import com.nwu.inquiry.model.vo.web.WebQueryOneFinishedInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationListVo;
import com.nwu.inquiry.service.IInquiryApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author lcy
 * @Description TODO
 * @DateTime 2024/4/2 17:59
 **/
@RestController
@RequestMapping("web/inquiry-application")
@Api(tags = "web问诊申请接口")
public class WebInquiryApplicationController {
    @Autowired
    IInquiryApplicationService iInquiryApplicationService;

    @ApiOperation(value = "查询等待中问诊申请列表")
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

    @ApiOperation(value = "查询等待中问诊申请详情")
    @GetMapping("/query-waiting-detail")
    public Result<WebQueryWaitingInquiryApplicationDetailVo> queryWaitingInquiryApplicationDetail(Long id) {
        WebQueryWaitingInquiryApplicationDetailVo webQueryWaitingInquiryApplicationDetailVo = iInquiryApplicationService.webQueryWaitingInquiryApplicationDetail(id);
        return Result.success(webQueryWaitingInquiryApplicationDetailVo);
    }


    @ApiOperation(value = "接诊")
    @GetMapping("/reception")
    public Result reception(Long id) {
        iInquiryApplicationService.changeWaitingToDoing(id);
        return Result.success();
    }

    @ApiOperation(value = "查询所有已完成问诊")
    @GetMapping("/allFinished")
    public Result<List<WebQueryAllFinishedInquiryVo>> allFinished() {
        List<WebQueryAllFinishedInquiryVo> webQueryAllFinishedInquiryVos = iInquiryApplicationService.webQueryFinishedInquiryApplications();
        return Result.success(webQueryAllFinishedInquiryVos);
    }

    @ApiOperation(value = "查询单个已完成问诊详情")
    @GetMapping("/queryFinishedById")
    public Result<WebQueryOneFinishedInquiryApplicationDetailVo> queryFinishedById(Long id) {
        WebQueryOneFinishedInquiryApplicationDetailVo webQueryOneFinishedInquiryApplicationDetailVo = iInquiryApplicationService.webQueryFinishedById(id);
        return Result.success(webQueryOneFinishedInquiryApplicationDetailVo);
    }


}
