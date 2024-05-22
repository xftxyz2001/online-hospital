package com.nwu.inquiry.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.inquiry.model.po.ChatUserLink;

/**
 * <p>
 * 用户聊天关系表 服务类
 * </p>
 *
 * @author author
 * @since 2024-04-07
 */
public interface IChatUserLinkService extends IService<ChatUserLink> {

    void webAddChatUserLink(ChatUserLink chatUserLink);
}
