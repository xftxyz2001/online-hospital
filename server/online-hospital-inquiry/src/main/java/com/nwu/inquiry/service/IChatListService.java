package com.nwu.inquiry.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.inquiry.model.po.ChatList;
import com.nwu.inquiry.model.vo.app.AppQueryChatListVo;
import com.nwu.inquiry.model.vo.app.UnreadNumber;
import com.nwu.inquiry.model.vo.web.WebQueryChatListVo;

import java.util.List;


public interface IChatListService extends IService<ChatList> {

    void webAddChatList(ChatList chatList);

    List<WebQueryChatListVo> webQueryChatList();

    void webInWindow(Long linkId);

    void webOutWindow(Long linkId);

    void webClearUnread(Long linkId);

    void appInWindow(Long linkId);

    void appOutWindow(Long linkId);

    void appClearUnread(Long linkId);

    void wsInWindow(UserIdAndIdentity userIdentity, Long linkId);

    void wsOutWindow(UserIdAndIdentity userIdentity, Long linkId);

    List<AppQueryChatListVo> appQueryChatList();

    UnreadNumber appQueryAllUnreadNo();
}
