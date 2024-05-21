package com.nwu.inquiry.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.inquiry.model.po.ChatList;
import com.nwu.inquiry.model.vo.app.AppQueryChatListVo;
import com.nwu.inquiry.model.vo.app.UnreadNumber;
import com.nwu.inquiry.model.vo.web.WebQueryChatListVo;
import com.nwu.inquiry.model.ws.UserIdentity;

import java.util.List;

/**
 * <p>
 * 聊天列表表 服务类
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
public interface IChatListService extends IService<ChatList> {

    void webAddChatList(ChatList chatList);

    List<WebQueryChatListVo> webQueryChatList();

    void webInWindow(Long linkId);

    void webOutWindow(Long linkId);

    void webClearUnread(Long linkId);

    void appInWindow(Long linkId);

    void appOutWindow(Long linkId);

    void appClearUnread(Long linkId);

    void wsInWindow(UserIdentity userIdentity, Long linkId);

    void wsOutWindow(UserIdentity userIdentity, Long linkId);

    List<AppQueryChatListVo> appQueryChatList();

    UnreadNumber appQueryAllUnreadNo();
}
