package com.nwu.inquiry.api;


import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.service.IInquiryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/inquiry-application")
public class InquiryApplicationController {
    @Autowired
    IInquiryApplicationService iInquiryApplicationService;

    @PostMapping("/list")
    @Operation(summary = "问诊申请查询接口")
    public Result<PageResult<InquiryApplication>> list(PageParams pageParams) {
        return iInquiryApplicationService.queryInquiryApplicationList(pageParams);
    }
}
