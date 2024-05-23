package com.nwu.inquiry.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.context.BaseContext;
import com.nwu.base.model.PageParams;
import com.nwu.base.model.PageResult;
import com.nwu.base.model.Result;
import com.nwu.inquiry.clients.HospitalClient;
import com.nwu.inquiry.clients.UserClient;
import com.nwu.inquiry.mapper.ChatListMapper;
import com.nwu.inquiry.mapper.ChatMessageMapper;
import com.nwu.inquiry.mapper.ChatUserLinkMapper;
import com.nwu.inquiry.mapper.InquiryApplicationMapper;
import com.nwu.inquiry.model.clientspo.PatientInfo;
import com.nwu.inquiry.model.dto.app.AddInquiryApplicationDto;
import com.nwu.inquiry.model.po.ChatList;
import com.nwu.inquiry.model.po.ChatUserLink;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.app.AppQueryAllApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryOneApplicationVo;
import com.nwu.inquiry.model.vo.app.AppQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryAllFinishedInquiryVo;
import com.nwu.inquiry.model.vo.web.WebQueryOneFinishedInquiryApplicationDetailVo;
import com.nwu.inquiry.model.vo.web.WebQueryWaitingInquiryApplicationDetailVo;
import com.nwu.inquiry.service.IChatListService;
import com.nwu.inquiry.service.IChatUserLinkService;
import com.nwu.inquiry.service.IInquiryApplicationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class InquiryApplicationServiceImpl extends ServiceImpl<InquiryApplicationMapper, InquiryApplication> implements IInquiryApplicationService {

    @Autowired
    InquiryApplicationMapper inquiryApplicationMapper;
    @Autowired
    HospitalClient hospitalClient;
    @Autowired
    UserClient userClient;
    @Autowired
    IChatUserLinkService iChatUserLinkService;
    @Autowired
    IChatListService iChatListService;
    @Autowired
    ChatMessageMapper chatMessageMapper;
    @Autowired
    ChatListMapper chatListMapper;
    @Autowired
    ChatUserLinkMapper chatUserLinkMapper;

    @Override
    public void appAddInquiryApplication(AddInquiryApplicationDto addInquiryApplicationDto) {
        Long hospitalId = addInquiryApplicationDto.getHospitalId();
        Long patientId = addInquiryApplicationDto.getPatientId();
        Long doctorId = addInquiryApplicationDto.getDoctorId();
        Long outpatientId = addInquiryApplicationDto.getOutpatientId();
        InquiryApplication inquiryApplication = new InquiryApplication();
        BeanUtils.copyProperties(addInquiryApplicationDto, inquiryApplication);

        String hospitalName = hospitalClient.getHospitalInfoById(hospitalId).getData().getName();
        String doctorName = hospitalClient.queryDoctorById(doctorId).getData().getName();
        String outpatientName = hospitalClient.queryOutpatientById(outpatientId).getData().getName();
        String patientName = userClient.getPatientInfoById(patientId).getData().getName();
        String userName = userClient.getUserInfoById(BaseContext.getUserIdentity().getId()).getData().getUsername();
        inquiryApplication.setDoctorName(doctorName);
        inquiryApplication.setOutpatientName(outpatientName);
        inquiryApplication.setUserName(userName);
        inquiryApplication.setPatientName(patientName);
        inquiryApplication.setHospitalName(hospitalName);
        inquiryApplication.setStatus(0);
        inquiryApplication.setUserId(BaseContext.getUserIdentity().getId());
        inquiryApplication.setCreateTime(LocalDateTime.now());
        inquiryApplication.setRemainNumber(10);
        inquiryApplicationMapper.insert(inquiryApplication);
    }

    @Override
    public List<InquiryApplication> webQueryWaitingInquiryApplicationList() {
        LambdaQueryWrapper<InquiryApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InquiryApplication::getDoctorId, BaseContext.getUserIdentity().getId());//当前医生
        lambdaQueryWrapper.eq(InquiryApplication::getStatus, 0);//等待状态
        List<InquiryApplication> inquiryApplications = inquiryApplicationMapper.selectList(lambdaQueryWrapper);
        return inquiryApplications;
    }

    @Override
    public WebQueryWaitingInquiryApplicationDetailVo webQueryWaitingInquiryApplicationDetail(Long id) {

        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(id);
        Long patientId = inquiryApplication.getPatientId();
        PatientInfo patientInfo = userClient.getPatientInfoById(patientId).getData();
        WebQueryWaitingInquiryApplicationDetailVo webQueryWaitingInquiryApplicationDetailVo = new WebQueryWaitingInquiryApplicationDetailVo();
        BeanUtils.copyProperties(inquiryApplication, webQueryWaitingInquiryApplicationDetailVo);
        webQueryWaitingInquiryApplicationDetailVo.setSex(patientInfo.getSex());
        webQueryWaitingInquiryApplicationDetailVo.setBirthdate(patientInfo.getBirthdate());
        webQueryWaitingInquiryApplicationDetailVo.setCardNo(patientInfo.getCardNo());
        webQueryWaitingInquiryApplicationDetailVo.setIsMarry(patientInfo.getIsMarry());
        return webQueryWaitingInquiryApplicationDetailVo;
    }


    @Override
    public AppQueryWaitingInquiryApplicationDetailVo appQueryWaitingInquiryApplicationDetail() {
        LambdaQueryWrapper<InquiryApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InquiryApplication::getStatus, 0);
        lambdaQueryWrapper.eq(InquiryApplication::getUserId, BaseContext.getUserIdentity().getId());
        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectOne(lambdaQueryWrapper);
        AppQueryWaitingInquiryApplicationDetailVo appQueryWaitingInquiryApplicationDetailVo = new AppQueryWaitingInquiryApplicationDetailVo();
        BeanUtils.copyProperties(inquiryApplication, appQueryWaitingInquiryApplicationDetailVo);
        Integer waitingNum = hospitalClient.queryDoctorById(inquiryApplication.getDoctorId()).getData().getWaitingNum();
        appQueryWaitingInquiryApplicationDetailVo.setWaitingNum(waitingNum);
        return appQueryWaitingInquiryApplicationDetailVo;
    }

    @Override
    public void changeWaitingToDoing(Long id) {
        //更新状态
        LambdaUpdateWrapper<InquiryApplication> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(InquiryApplication::getId, id);
        updateWrapper.set(InquiryApplication::getStatus, 1);
        inquiryApplicationMapper.update(null, updateWrapper);
        //添加用户聊天关系
        InquiryApplication inquiryApplication = getById(id);
        ChatUserLink chatUserLink = new ChatUserLink();
        chatUserLink.setFromUserId(inquiryApplication.getDoctorId())
                .setFromUserIdentity(1)
                .setFromUserName(inquiryApplication.getDoctorName())
                .setToUserId(inquiryApplication.getUserId())
                .setToUserName(inquiryApplication.getUserName())
                .setToUserIdentity(0)
                .setCreateTime(LocalDateTime.now())
                .setIsFinished(0)
                .setInquiryApplicationId(id);
        iChatUserLinkService.webAddChatUserLink(chatUserLink);
        //添加聊天列表
        ChatList chatList = new ChatList();
        chatList.setLinkId(chatUserLink.getId())
                .setFromUserId(inquiryApplication.getDoctorId())
                .setFromUserIdentity(1)
                .setFromUserName(inquiryApplication.getDoctorName())
                .setToUserId(inquiryApplication.getUserId())
                .setToUserName(inquiryApplication.getUserName())
                .setToUserIdentity(0)
                .setCreateTime(LocalDateTime.now())
                .setStatus(0)
                .setInquiryApplicationId(id)
                .setFromWindow(0)
                .setToWindow(0)
                .setUnread(0)
                .setLastMessageTime(LocalDateTime.now());//医生列表
        iChatListService.webAddChatList(chatList);
        ChatList chatList1 = new ChatList();
        chatList1.setLinkId(chatUserLink.getId())
                .setToUserId(inquiryApplication.getDoctorId())
                .setToUserIdentity(1)
                .setToUserName(inquiryApplication.getDoctorName())
                .setFromUserId(inquiryApplication.getUserId())
                .setFromUserName(inquiryApplication.getUserName())
                .setFromUserIdentity(0)
                .setCreateTime(LocalDateTime.now())
                .setStatus(0)
                .setInquiryApplicationId(id)
                .setFromWindow(0)
                .setToWindow(0)
                .setUnread(0)
                .setLastMessageTime(LocalDateTime.now());
        iChatListService.webAddChatList(chatList1);//用户列表
//        //增加一条聊天记录
//        ChatMessage chatMessage= new ChatMessage();
//        chatMessage.setLinkId(chatUserLink.getId())
//                .setInquiryApplicationId(inquiryApplication.getId())
//                .setFromUserId(inquiryApplication.getUserId())
//                .setFromUserIdentity(0)
//                .setFromUserName(inquiryApplication.getUserName())
//                .setToUserId(inquiryApplication.getDoctorId())
//                .setToUserName(inquiryApplication.getDoctorName())
//                .setToUserIdentity(1)
//                .setContent(inquiryApplication.getDescription())
//                .setSendTime(LocalDateTime.now())
//                .setType(0)
//                .setIsLatest(1);

//        chatMessageMapper.insert(chatMessage);
    }

    @Override
    public List<AppQueryAllApplicationVo> appQueryAll() {
        LambdaQueryWrapper<InquiryApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InquiryApplication::getUserId, BaseContext.getUserIdentity().getId())
                .orderByDesc(InquiryApplication::getCreateTime);
        List<InquiryApplication> inquiryApplications = inquiryApplicationMapper.selectList(lambdaQueryWrapper);
        List<AppQueryAllApplicationVo> appQueryAllApplicationVos = new ArrayList<>();
        for (InquiryApplication inquiryApplication : inquiryApplications) {
            AppQueryAllApplicationVo appQueryAllApplicationVo = new AppQueryAllApplicationVo();
            BeanUtils.copyProperties(inquiryApplication, appQueryAllApplicationVo);
            appQueryAllApplicationVos.add(appQueryAllApplicationVo);
        }
        return appQueryAllApplicationVos;
    }

    @Override
    public AppQueryOneApplicationVo appQueryById(Long id) {

        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(id);
        AppQueryOneApplicationVo appQueryOneApplicationVo = new AppQueryOneApplicationVo();
        BeanUtils.copyProperties(inquiryApplication, appQueryOneApplicationVo);
        LambdaQueryWrapper<ChatUserLink> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatUserLink::getInquiryApplicationId, id);
        ChatUserLink chatUserLink = chatUserLinkMapper.selectOne(lambdaQueryWrapper);
        if (chatUserLink != null)
            appQueryOneApplicationVo.setLinkId(chatUserLink.getId());
        return appQueryOneApplicationVo;
    }

    @Override
    public List<WebQueryAllFinishedInquiryVo> webQueryFinishedInquiryApplications() {
        LambdaQueryWrapper<InquiryApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InquiryApplication::getDoctorId, BaseContext.getUserIdentity().getId())
                .eq(InquiryApplication::getStatus, 2);
        List<InquiryApplication> inquiryApplications = inquiryApplicationMapper.selectList(lambdaQueryWrapper);
        List<WebQueryAllFinishedInquiryVo> webQueryAllFinishedInquiryVos = new ArrayList<>();
        for (InquiryApplication inquiryApplication : inquiryApplications) {
            WebQueryAllFinishedInquiryVo webQueryAllFinishedInquiryVo = new WebQueryAllFinishedInquiryVo();
            BeanUtils.copyProperties(inquiryApplication, webQueryAllFinishedInquiryVo);
            webQueryAllFinishedInquiryVos.add(webQueryAllFinishedInquiryVo);
        }
        return webQueryAllFinishedInquiryVos;
    }

    @Override
    public WebQueryOneFinishedInquiryApplicationDetailVo webQueryFinishedById(Long id) {
        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(id);
        Long patientId = inquiryApplication.getPatientId();
        PatientInfo patientInfo = userClient.getPatientInfoById(patientId).getData();
        WebQueryOneFinishedInquiryApplicationDetailVo webQueryOneFinishedInquiryApplicationDetailVo = new WebQueryOneFinishedInquiryApplicationDetailVo();
        BeanUtils.copyProperties(inquiryApplication, webQueryOneFinishedInquiryApplicationDetailVo);
        webQueryOneFinishedInquiryApplicationDetailVo.setSex(patientInfo.getSex());
        webQueryOneFinishedInquiryApplicationDetailVo.setBirthdate(patientInfo.getBirthdate());
        webQueryOneFinishedInquiryApplicationDetailVo.setCardNo(patientInfo.getCardNo());
        webQueryOneFinishedInquiryApplicationDetailVo.setIsMarry(patientInfo.getIsMarry());
        LambdaQueryWrapper<ChatUserLink> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatUserLink::getInquiryApplicationId, id);
        ChatUserLink chatUserLink = chatUserLinkMapper.selectOne(lambdaQueryWrapper);
        if (chatUserLink != null)
            webQueryOneFinishedInquiryApplicationDetailVo.setLinkId(chatUserLink.getId());
        return webQueryOneFinishedInquiryApplicationDetailVo;
    }

    @Override
    public Result<PageResult<InquiryApplication>> queryInquiryApplicationList(PageParams pageParams) {

        Page<InquiryApplication> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<InquiryApplication> inquiryApplicationPage = inquiryApplicationMapper.selectPage(page, null);
        List<InquiryApplication> inquiryApplications = inquiryApplicationPage.getRecords();
        Long counts = inquiryApplicationPage.getTotal();
        PageResult<InquiryApplication> inquiryApplicationPageResult = new PageResult<>(inquiryApplications, counts, pageParams.getPageNo(), pageParams.getPageSize());
        return Result.success(inquiryApplicationPageResult);

    }
}
