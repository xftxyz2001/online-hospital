package com.nwu.inquiry.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nwu.inquiry.mapper.ChatUserLinkMapper;
import com.nwu.inquiry.model.po.ChatUserLink;
import com.nwu.inquiry.service.IChatUserLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatUserLinkServiceImpl extends ServiceImpl<ChatUserLinkMapper, ChatUserLink> implements IChatUserLinkService {

    @Autowired
    ChatUserLinkMapper chatUserLinkMapper;

    @Override
    public void webAddChatUserLink(ChatUserLink chatUserLink) {

        chatUserLinkMapper.insert(chatUserLink);
    }
}
