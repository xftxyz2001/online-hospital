package com.nwu.inquiry.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.context.BaseContext;
import com.nwu.inquiry.model.vo.app.AppChatMessageVo;
import com.nwu.inquiry.clients.HospitalClient;
import com.nwu.inquiry.clients.UserClient;
import com.nwu.inquiry.mapper.ChatListMapper;
import com.nwu.inquiry.mapper.ChatMessageMapper;
import com.nwu.inquiry.mapper.ChatUserLinkMapper;
import com.nwu.inquiry.mapper.InquiryApplicationMapper;
import com.nwu.inquiry.model.clientspo.DoctorInfo;
import com.nwu.inquiry.model.dto.app.AppAddChatMessageDto;
import com.nwu.inquiry.model.dto.web.WebAddChatMessageDto;
import com.nwu.inquiry.model.po.ChatList;
import com.nwu.inquiry.model.po.ChatMessage;
import com.nwu.inquiry.model.po.ChatUserLink;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.app.AppQueryChatMessageVo;
import com.nwu.inquiry.model.vo.web.WebChatMessageVo;
import com.nwu.inquiry.model.vo.web.WebQueryChatMessageVo;
import com.nwu.inquiry.model.ws.WsChatMessageDto;
import com.nwu.inquiry.service.IChatMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 聊天内容详情表 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements IChatMessageService {

    @Autowired
    ChatMessageMapper chatMessageMapper;
    @Autowired
    UserClient userClient;
    @Autowired
    HospitalClient hospitalClient;
    @Autowired
    ChatListMapper chatListMapper;
    @Autowired
    ChatUserLinkMapper chatUserLinkMapper;
    @Autowired
    InquiryApplicationMapper inquiryApplicationMapper;

    @Override
    public void webAddChatMessage(WebAddChatMessageDto webAddChatMessageDto) {
        //先判断对方是否在线，不在线的话对方未读数+1
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper.eq(ChatList::getLinkId, webAddChatMessageDto.getLinkId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserId, webAddChatMessageDto.getToUserId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserIdentity, 0);
        chatListLambdaUpdateWrapper.eq(ChatList::getFromWindow, 0);
        chatListLambdaUpdateWrapper.setSql("unread=unread+1");
        chatListMapper.update(null, chatListLambdaUpdateWrapper);

        //将上一条最后一条消息状态改为否
        LambdaUpdateWrapper<ChatMessage> chatMessageLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatMessageLambdaUpdateWrapper.eq(ChatMessage::getLinkId, webAddChatMessageDto.getLinkId())
                .eq(ChatMessage::getIsLatest, 1)
                .set(ChatMessage::getIsLatest, 0);
        chatMessageMapper.update(null, chatMessageLambdaUpdateWrapper);
        //修改最后一条消息时间
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper1.eq(ChatList::getLinkId, webAddChatMessageDto.getLinkId())
                .set(ChatList::getLastMessageTime, LocalDateTime.now());
        chatListMapper.update(null, chatListLambdaUpdateWrapper1);
        //往聊天详情表插入聊天信息数据
        Long toUserId = webAddChatMessageDto.getToUserId();
        String username = userClient.getUserInfoById(toUserId).getData().getUsername();
        String doctorName = hospitalClient.getHospitalInfoById(BaseContext.getUserIdentity().getId()).getData().getName();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(webAddChatMessageDto.getContent())
                .setFromUserIdentity(1)
                .setFromUserId(BaseContext.getUserIdentity().getId())
                .setFromUserName(doctorName)
                .setLinkId(webAddChatMessageDto.getLinkId())
                .setInquiryApplicationId(webAddChatMessageDto.getInquiryApplicationId())
                .setToUserId(webAddChatMessageDto.getToUserId())
                .setToUserIdentity(0)
                .setToUserName(username)
                .setSendTime(LocalDateTime.now())
                .setType(webAddChatMessageDto.getType())
                .setIsLatest(1);
        chatMessageMapper.insert(chatMessage);
    }

    @Override
    public void appAddChatMessage(AppAddChatMessageDto appAddChatMessageDto) {
        //判断剩余聊天次数
        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(appAddChatMessageDto.getInquiryApplicationId());
        if (inquiryApplication.getRemainNumber() <= 0) return;
        LambdaUpdateWrapper<InquiryApplication> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(InquiryApplication::getId, appAddChatMessageDto.getInquiryApplicationId())
                .setSql("remain_number=remain_number-1");

        inquiryApplicationMapper.update(null, lambdaUpdateWrapper);
        //先判断对方是否在线，不在线的话对方未读数+1
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper.eq(ChatList::getLinkId, appAddChatMessageDto.getLinkId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserId, appAddChatMessageDto.getToUserId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserIdentity, 1);
        chatListLambdaUpdateWrapper.eq(ChatList::getFromWindow, 0);
        chatListLambdaUpdateWrapper.setSql("unread=unread+1");
        chatListMapper.update(null, chatListLambdaUpdateWrapper);
        //修改最后一条消息时间
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper1.eq(ChatList::getLinkId, appAddChatMessageDto.getLinkId())
                .set(ChatList::getLastMessageTime, LocalDateTime.now());
        chatListMapper.update(null, chatListLambdaUpdateWrapper1);
        //将上一条最后一条消息状态改为否
        LambdaUpdateWrapper<ChatMessage> chatMessageLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatMessageLambdaUpdateWrapper.eq(ChatMessage::getLinkId, appAddChatMessageDto.getLinkId())
                .eq(ChatMessage::getIsLatest, 1)
                .set(ChatMessage::getIsLatest, 0);
        chatMessageMapper.update(null, chatMessageLambdaUpdateWrapper);
        //往聊天详情表插入聊天信息数据
        Long toUserId = appAddChatMessageDto.getToUserId();
        String doctorName = hospitalClient.queryDoctorById(toUserId).getData().getName();
        String username = userClient.getUserInfoById(BaseContext.getUserIdentity().getId()).getData().getUsername();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(appAddChatMessageDto.getContent())
                .setToUserIdentity(1)
                .setToUserId(appAddChatMessageDto.getToUserId())
                .setToUserName(doctorName)
                .setLinkId(appAddChatMessageDto.getLinkId())
                .setInquiryApplicationId(appAddChatMessageDto.getInquiryApplicationId())
                .setFromUserId(BaseContext.getUserIdentity().getId())
                .setFromUserIdentity(0)
                .setFromUserName(username)
                .setSendTime(LocalDateTime.now())
                .setType(appAddChatMessageDto.getType())
                .setIsLatest(1);
        chatMessageMapper.insert(chatMessage);
    }

    @Override
    public WebQueryChatMessageVo webQueryChatMessage(Long linkId) {
        ChatUserLink chatUserLink = chatUserLinkMapper.selectById(linkId);
        WebQueryChatMessageVo webQueryChatMessageVo = new WebQueryChatMessageVo();
        //获取问诊申请信息
        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(chatUserLink.getInquiryApplicationId());
        webQueryChatMessageVo.setDescription(inquiryApplication.getDescription());
        webQueryChatMessageVo.setImage(inquiryApplication.getImage());
        webQueryChatMessageVo.setRemainNumber(inquiryApplication.getRemainNumber());

        // 封装返回结果
        Long toUserId;
        String toUserName;
        Long doctorId;
        if (chatUserLink.getFromUserIdentity() == 0) { // from是用户,设置用户名、用户头像
            toUserId = chatUserLink.getFromUserId(); // 用户id
            toUserName = chatUserLink.getFromUserName(); // 用户名
            doctorId = chatUserLink.getToUserId();//医生id
        } else {//from是医生，设置用户名、医生头像
            toUserId = chatUserLink.getToUserId();
            toUserName = chatUserLink.getToUserName();
            doctorId = chatUserLink.getFromUserId();//医生id
        }
        webQueryChatMessageVo.setToUserId(toUserId);
        webQueryChatMessageVo.setToUserName(toUserName);
        webQueryChatMessageVo.setToUserPicture(userClient.getUserInfoById(toUserId).getData().getAvatar());
        webQueryChatMessageVo.setFromUserId(doctorId); // 医生id
        webQueryChatMessageVo.setFromUserPicture(hospitalClient.queryDoctorById(doctorId).getData().getPicture());

        //获取linkId，问诊申请id，接收方id
        webQueryChatMessageVo.setLinkId(linkId);
        webQueryChatMessageVo.setInquiryApplicationId(chatUserLink.getInquiryApplicationId());
        //获取聊天消息列表
        LambdaQueryWrapper<ChatMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatMessage::getLinkId, linkId).and(Wrapper -> Wrapper.eq(ChatMessage::getFromUserId, BaseContext.getUserIdentity().getId()).or().eq(ChatMessage::getToUserId, BaseContext.getUserIdentity().getId())).orderBy(true, true, ChatMessage::getSendTime);
        List<ChatMessage> chatMessages = chatMessageMapper.selectList(lambdaQueryWrapper);
        List<WebChatMessageVo> webChatMessageVos = new ArrayList<>();
        for (ChatMessage chatMessage : chatMessages) {
            WebChatMessageVo webChatMessageVo = new WebChatMessageVo();
            webChatMessageVo.setIdentity(chatMessage.getFromUserIdentity());
            webChatMessageVo.setType(chatMessage.getType());
            webChatMessageVo.setContent(chatMessage.getContent());
            LocalDateTime sendTime = chatMessage.getSendTime();
            if (sendTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                String time = sendTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                webChatMessageVo.setSendTime(time);
            } else {
                String time = sendTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
                webChatMessageVo.setSendTime(time);
            }
            webChatMessageVos.add(webChatMessageVo);
        }
        webQueryChatMessageVo.setMessageVos(webChatMessageVos);
        return webQueryChatMessageVo;
    }

    @Override
    public AppQueryChatMessageVo appQueryChatMessage(Long linkId) {
        //设置linkId,问诊申请id
        AppQueryChatMessageVo appQueryChatMessageVo = new AppQueryChatMessageVo();
        appQueryChatMessageVo.setLinkId(linkId);
        ChatUserLink chatUserLink = chatUserLinkMapper.selectById(linkId);
        appQueryChatMessageVo.setInquiryApplicationId(chatUserLink.getInquiryApplicationId());
        //获取问诊申请信息
        InquiryApplication inquiryApplication = inquiryApplicationMapper.selectById(chatUserLink.getInquiryApplicationId());
        appQueryChatMessageVo.setDescription(inquiryApplication.getDescription());
        appQueryChatMessageVo.setImage(inquiryApplication.getImage());
        appQueryChatMessageVo.setRemainNumber(inquiryApplication.getRemainNumber());
        //获取医生id
        Long doctorId;
        if (chatUserLink.getFromUserIdentity() == 1) {
            String toAvatar = hospitalClient.queryDoctorById(chatUserLink.getFromUserId()).getData().getPicture();
            appQueryChatMessageVo.setToUserPicture(toAvatar);
            String fromAvatar = userClient.getUserInfoById(chatUserLink.getToUserId()).getData().getAvatar();
            appQueryChatMessageVo.setFromUserPicture(fromAvatar);
            doctorId = chatUserLink.getFromUserId();
        } else {
            String toAvatar = hospitalClient.queryDoctorById(chatUserLink.getToUserId()).getData().getPicture();
            appQueryChatMessageVo.setToUserPicture(toAvatar);
            String fromAvatar = userClient.getUserInfoById(chatUserLink.getFromUserId()).getData().getAvatar();
            appQueryChatMessageVo.setFromUserPicture(fromAvatar);
            doctorId = chatUserLink.getFromUserId();
            doctorId = chatUserLink.getToUserId();
        }
        appQueryChatMessageVo.setDoctorId(doctorId);
        //设置医生信息
        DoctorInfo doctorInfo = hospitalClient.queryDoctorById(doctorId).getData();
        appQueryChatMessageVo.setDoctorName(doctorInfo.getName());
        appQueryChatMessageVo.setProfessionalTitle(doctorInfo.getProfessionalTitle());
        appQueryChatMessageVo.setIntroduce(doctorInfo.getIntroduce());

        //设置聊天信息
        LambdaQueryWrapper<ChatMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatMessage::getLinkId, linkId).and(Wrapper -> Wrapper.eq(ChatMessage::getFromUserId, BaseContext.getUserIdentity().getId()).or().eq(ChatMessage::getToUserId, BaseContext.getUserIdentity().getId())).orderBy(true, true, ChatMessage::getSendTime);
        List<ChatMessage> chatMessages = chatMessageMapper.selectList(lambdaQueryWrapper);
        List<AppChatMessageVo> appChatMessageVos = new ArrayList<>();
        for (ChatMessage chatMessage : chatMessages) {
            AppChatMessageVo appChatMessageVo = new AppChatMessageVo();
            appChatMessageVo.setIdentity(chatMessage.getFromUserIdentity());
            appChatMessageVo.setType(chatMessage.getType());
            appChatMessageVo.setContent(chatMessage.getContent());
            LocalDateTime sendTime = chatMessage.getSendTime();
            if (sendTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                String time = sendTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                appChatMessageVo.setSendTime(time);
            } else {
                String time = sendTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
                appChatMessageVo.setSendTime(time);
            }

            appChatMessageVos.add(appChatMessageVo);
        }
        appQueryChatMessageVo.setMessageVos(appChatMessageVos);
        return appQueryChatMessageVo;
    }

    @Override
    public void wsAddChatMessage(WsChatMessageDto wsChatMessageDto) {
        //先判断对方是否在线，不在线的话对方未读数+1
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper.eq(ChatList::getLinkId, wsChatMessageDto.getLinkId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserId, wsChatMessageDto.getToUserId());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromUserIdentity, wsChatMessageDto.getToUserIdentity());
        chatListLambdaUpdateWrapper.eq(ChatList::getFromWindow, 0);
        chatListLambdaUpdateWrapper.setSql("unread=unread+1");
        chatListMapper.update(null, chatListLambdaUpdateWrapper);

        //将上一条最后一条消息状态改为否
        LambdaUpdateWrapper<ChatMessage> chatMessageLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        chatMessageLambdaUpdateWrapper.eq(ChatMessage::getLinkId, wsChatMessageDto.getLinkId())
                .eq(ChatMessage::getIsLatest, 1)
                .set(ChatMessage::getIsLatest, 0);
        chatMessageMapper.update(null, chatMessageLambdaUpdateWrapper);


        Integer fromUserIdentity = wsChatMessageDto.getFromUserIdentity();
        String fromUserName, toUserName;
        if (fromUserIdentity == 0) {
            fromUserName = userClient.getUserInfoById(wsChatMessageDto.getFromUserId()).getData().getUsername();
            toUserName = hospitalClient.queryDoctorById(wsChatMessageDto.getToUserId()).getData().getName();
        } else {
            fromUserName = hospitalClient.queryDoctorById(wsChatMessageDto.getFromUserId()).getData().getName();
            toUserName = userClient.getUserInfoById(wsChatMessageDto.getToUserId()).getData().getUsername();
        }
        ChatMessage chatMessage = new ChatMessage();
        BeanUtils.copyProperties(wsChatMessageDto, chatMessage);
        chatMessage.setIsLatest(1)
                .setSendTime(LocalDateTime.now())
                .setFromUserName(fromUserName)
                .setToUserName(toUserName);
        chatMessageMapper.insert(chatMessage);

    }


}
