package com.nwu.inquiry.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.inquiry.model.po.ChatUserLink;


public interface IChatUserLinkService extends IService<ChatUserLink> {

    void webAddChatUserLink(ChatUserLink chatUserLink);
}
