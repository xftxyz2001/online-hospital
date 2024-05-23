package com.nwu.inquiry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.inquiry.model.dto.app.AddInquiryApplicationDto;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.app.AppQueryAllApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryOneApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryAllFinishedInquiryVo;
import com.nwu.inquiry.model.vo.web.WebQueryOneFinishedInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationDetailVo;

import java.util.List;

public interface IInquiryApplicationService extends IService<InquiryApplication> {

    void appAddInquiryApplication(AddInquiryApplicationDto addInquiryApplicationDto);

    List<InquiryApplication> webQueryWaitingInquiryApplicationList();

    WebQueryWaitingInquiryApplicationDetailVo webQueryWaitingInquiryApplicationDetail(Long id);

    AppQueryWaitingInquiryApplicationDetailVo appQueryWaitingInquiryApplicationDetail();

    Long changeWaitingToDoing(Long id);

    List<AppQueryAllApplicationVo> appQueryAll();

    AppQueryOneApplicationVo appQueryById(Long id);

    List<WebQueryAllFinishedInquiryVo> webQueryFinishedInquiryApplications();

    WebQueryOneFinishedInquiryApplicationDetailVo webQueryFinishedById(Long id);

    Result<PageResult<InquiryApplication>> queryInquiryApplicationList(PageParams pageParams);
}
