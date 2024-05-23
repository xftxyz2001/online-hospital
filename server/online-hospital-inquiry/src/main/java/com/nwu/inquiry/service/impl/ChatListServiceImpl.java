package com.nwu.inquiry.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.base.context.BaseContext;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.inquiry.clients.HospitalClient;
import com.nwu.inquiry.clients.UserClient;
import com.nwu.inquiry.mapper.ChatListMapper;
import com.nwu.inquiry.mapper.ChatMessageMapper;
import com.nwu.inquiry.mapper.InquiryApplicationMapper;
import com.nwu.inquiry.model.po.ChatList;
import com.nwu.inquiry.model.po.ChatMessage;
import com.nwu.inquiry.model.po.InquiryApplication;
import com.nwu.inquiry.model.vo.app.AppQueryChatListVo;
import com.nwu.inquiry.model.vo.app.UnreadNumber;
import com.nwu.inquiry.model.vo.web.WebQueryChatListVo;
import com.nwu.inquiry.service.IChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChatListServiceImpl extends ServiceImpl<ChatListMapper, ChatList> implements IChatListService {

    @Autowired
    ChatListMapper chatListMapper;

    @Autowired
    ChatMessageMapper chatMessageMapper;

    @Autowired
    UserClient userClient;
    @Autowired
    HospitalClient hospitalClient;
    @Autowired
    InquiryApplicationMapper inquiryApplicationMapper;

    @Override
    public void webAddChatList(ChatList chatList) {
        chatListMapper.insert(chatList);
    }

    @Override
    public List<WebQueryChatListVo> webQueryChatList() {
        LambdaQueryWrapper<ChatList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaQueryWrapper.eq(ChatList::getFromUserIdentity, 1);
        lambdaQueryWrapper.eq(ChatList::getStatus, 0);//未完成
        lambdaQueryWrapper.orderByDesc(ChatList::getLastMessageTime);
        List<ChatList> chatLists = chatListMapper.selectList(lambdaQueryWrapper);
        List<WebQueryChatListVo> webQueryChatListVos = new ArrayList<>();
        for (ChatList chatList : chatLists) {
            Duration duration = Duration.between(chatList.getCreateTime(), LocalDateTime.now());
            if (duration.toHours() >= 48) {
                LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(ChatList::getId, chatList.getId())
                        .set(ChatList::getStatus, 1);//修改为已完成
                chatListMapper.update(null, lambdaUpdateWrapper);
                LambdaUpdateWrapper<InquiryApplication> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper1.eq(InquiryApplication::getId, chatList.getInquiryApplicationId())
                        .set(InquiryApplication::getStatus, 2);
                inquiryApplicationMapper.update(null, lambdaUpdateWrapper1);
                continue;
            }
            WebQueryChatListVo webQueryChatListVo = new WebQueryChatListVo();
            webQueryChatListVo.setLinkId(chatList.getLinkId());
            webQueryChatListVo.setListId(chatList.getId());
            webQueryChatListVo.setToUserName(chatList.getToUserName());
            webQueryChatListVo.setUnread(chatList.getUnread());
            webQueryChatListVo.setToUserPicture(userClient.getUserInfoById(chatList.getToUserId()).getData().getAvatar());
            LambdaQueryWrapper<ChatMessage> chatMessageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            chatMessageLambdaQueryWrapper.eq(ChatMessage::getLinkId, chatList.getLinkId());
            chatMessageLambdaQueryWrapper.eq(ChatMessage::getIsLatest, 1);
            ChatMessage chatMessage = chatMessageMapper.selectOne(chatMessageLambdaQueryWrapper);
            LocalDateTime createTime = LocalDateTime.now();
            if (chatMessage == null) {
                String lastMessage = "";
                webQueryChatListVo.setLastMessage(lastMessage);
                webQueryChatListVo.setType(2);
                createTime = chatList.getLastMessageTime();
            } else {
                String lastMessage = chatMessage.getContent();
                Integer type = chatMessage.getType();
                webQueryChatListVo.setLastMessage(lastMessage);
                webQueryChatListVo.setType(type);
                createTime = chatMessage.getSendTime();

            }
            if (createTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                String time = createTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                webQueryChatListVo.setLastTime(time);
            } else {
                String time = createTime.format(DateTimeFormatter.ofPattern("MM-dd"));
                webQueryChatListVo.setLastTime(time);
            }
            webQueryChatListVos.add(webQueryChatListVo);
        }
        return webQueryChatListVos;
    }

    @Override
    public void webInWindow(Long linkId) {
        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, 1);
        lambdaUpdateWrapper1.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper1.set(ChatList::getFromWindow, 1);
        chatListMapper.update(null, lambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, 1);
        lambdaUpdateWrapper2.eq(ChatList::getToUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper2.set(ChatList::getToWindow, 1);
        chatListMapper.update(null, lambdaUpdateWrapper2);
    }

    @Override
    public void webOutWindow(Long linkId) {

        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, 1);
        lambdaUpdateWrapper1.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper1.set(ChatList::getFromWindow, 0);
        chatListMapper.update(null, lambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, 1);
        lambdaUpdateWrapper2.eq(ChatList::getToUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper2.set(ChatList::getToWindow, 0);
        chatListMapper.update(null, lambdaUpdateWrapper2);
    }

    @Override
    public void webClearUnread(Long linkId) {
        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(ChatList::getFromUserIdentity, 1);
        lambdaUpdateWrapper.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper.set(ChatList::getUnread, 0);
        chatListMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public void appInWindow(Long linkId) {
        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, 0);
        lambdaUpdateWrapper1.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper1.set(ChatList::getFromWindow, 1);
        chatListMapper.update(null, lambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, 0);
        lambdaUpdateWrapper2.eq(ChatList::getToUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper2.set(ChatList::getToWindow, 1);
        chatListMapper.update(null, lambdaUpdateWrapper2);
    }

    @Override
    public void appOutWindow(Long linkId) {
        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, 0);
        lambdaUpdateWrapper1.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper1.set(ChatList::getFromWindow, 0);
        chatListMapper.update(null, lambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, 0);
        lambdaUpdateWrapper2.eq(ChatList::getToUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper2.set(ChatList::getToWindow, 0);
        chatListMapper.update(null, lambdaUpdateWrapper2);
    }

    @Override
    public void appClearUnread(Long linkId) {
        LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(ChatList::getFromUserIdentity, 0);
        lambdaUpdateWrapper.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaUpdateWrapper.eq(ChatList::getLinkId, linkId);
        lambdaUpdateWrapper.set(ChatList::getUnread, 0);
        chatListMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public void wsInWindow(UserIdAndIdentity userIdentity, Long linkId) {
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper1.eq(ChatList::getFromUserId, userIdentity.getId());
        chatListLambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, userIdentity.getIdentity());
        chatListLambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        chatListLambdaUpdateWrapper1.set(ChatList::getFromWindow, 1);
        chatListMapper.update(null, chatListLambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper2.eq(ChatList::getToUserId, userIdentity.getId());
        chatListLambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, userIdentity.getIdentity());
        chatListLambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        chatListLambdaUpdateWrapper2.set(ChatList::getToWindow, 1);
        chatListMapper.update(null, chatListLambdaUpdateWrapper2);
    }

    @Override
    public void wsOutWindow(UserIdAndIdentity userIdentity, Long linkId) {
        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper1.eq(ChatList::getFromUserId, userIdentity.getId());
        chatListLambdaUpdateWrapper1.eq(ChatList::getFromUserIdentity, userIdentity.getIdentity());
        chatListLambdaUpdateWrapper1.eq(ChatList::getLinkId, linkId);
        chatListLambdaUpdateWrapper1.set(ChatList::getFromWindow, 0);
        chatListMapper.update(null, chatListLambdaUpdateWrapper1);

        LambdaUpdateWrapper<ChatList> chatListLambdaUpdateWrapper2 = new LambdaUpdateWrapper<>();
        chatListLambdaUpdateWrapper2.eq(ChatList::getToUserId, userIdentity.getId());
        chatListLambdaUpdateWrapper2.eq(ChatList::getToUserIdentity, userIdentity.getIdentity());
        chatListLambdaUpdateWrapper2.eq(ChatList::getLinkId, linkId);
        chatListLambdaUpdateWrapper2.set(ChatList::getToWindow, 0);
        chatListMapper.update(null, chatListLambdaUpdateWrapper2);
    }

    @Override
    public List<AppQueryChatListVo> appQueryChatList() {
        LambdaQueryWrapper<ChatList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaQueryWrapper.eq(ChatList::getFromUserIdentity, 0);
        lambdaQueryWrapper.eq(ChatList::getStatus, 0);//未完成
        lambdaQueryWrapper.orderByDesc(ChatList::getLastMessageTime);
        List<ChatList> chatLists = chatListMapper.selectList(lambdaQueryWrapper);
        List<AppQueryChatListVo> appQueryChatListVos = new ArrayList<>();
        for (ChatList chatList : chatLists) {
            Duration duration = Duration.between(chatList.getCreateTime(), LocalDateTime.now());
            if (duration.toHours() >= 48) {
                LambdaUpdateWrapper<ChatList> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(ChatList::getId, chatList.getId())
                        .set(ChatList::getStatus, 1);//修改为已完成
                chatListMapper.update(null, lambdaUpdateWrapper);
                LambdaUpdateWrapper<InquiryApplication> lambdaUpdateWrapper1 = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper1.eq(InquiryApplication::getId, chatList.getInquiryApplicationId())
                        .set(InquiryApplication::getStatus, 2);
                inquiryApplicationMapper.update(null, lambdaUpdateWrapper1);
                continue;
            }
            AppQueryChatListVo appQueryChatListVo = new AppQueryChatListVo();
            appQueryChatListVo.setLinkId(chatList.getLinkId());
            appQueryChatListVo.setListId(chatList.getId());
            appQueryChatListVo.setToUserName(chatList.getToUserName());
            appQueryChatListVo.setUnread(chatList.getUnread());
            appQueryChatListVo.setToUserPicture(hospitalClient.queryDoctorById(chatList.getToUserId()).getData().getPicture());
            LambdaQueryWrapper<ChatMessage> chatMessageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            chatMessageLambdaQueryWrapper.eq(ChatMessage::getLinkId, chatList.getLinkId());
            chatMessageLambdaQueryWrapper.eq(ChatMessage::getIsLatest, 1);
            ChatMessage chatMessage = chatMessageMapper.selectOne(chatMessageLambdaQueryWrapper);
            LocalDateTime createTime = LocalDateTime.now();
            if (chatMessage == null) {
                String lastMessage = "";
                appQueryChatListVo.setLastMessage(lastMessage);
                appQueryChatListVo.setType(2);
                createTime = chatList.getLastMessageTime();
            } else {
                String lastMessage = chatMessage.getContent();
                Integer type = chatMessage.getType();
                appQueryChatListVo.setLastMessage(lastMessage);
                appQueryChatListVo.setType(type);
                createTime = chatMessage.getSendTime();

            }
            if (createTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                String time = createTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                appQueryChatListVo.setLastTime(time);
            } else {
                String time = createTime.format(DateTimeFormatter.ofPattern("MM-dd"));
                appQueryChatListVo.setLastTime(time);
            }
            appQueryChatListVos.add(appQueryChatListVo);
        }
        return appQueryChatListVos;
    }

    @Override
    public UnreadNumber appQueryAllUnreadNo() {
        LambdaQueryWrapper<ChatList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatList::getFromUserId, BaseContext.getUserIdentity().getId());
        lambdaQueryWrapper.eq(ChatList::getFromUserIdentity, 0);
        lambdaQueryWrapper.eq(ChatList::getStatus, 0);//未完成
        List<ChatList> chatLists = chatListMapper.selectList(lambdaQueryWrapper);
        int number = 0;
        for (ChatList chatList : chatLists) {
            number += chatList.getUnread();
        }
        UnreadNumber unreadNumber = new UnreadNumber();
        unreadNumber.setUnread(number);
        return unreadNumber;
    }
}
