package com.nwu.inquiry.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nwu.inquiry.model.dto.app.AppAddChatMessageDto;
import com.nwu.inquiry.model.dto.web.WebAddChatMessageDto;
import com.nwu.inquiry.model.po.ChatMessage;
import com.nwu.inquiry.model.vo.app.AppQueryChatMessageVo;
import com.nwu.inquiry.model.vo.web.WebQueryChatMessageVo;
import com.nwu.inquiry.model.ws.WsChatMessageDto;


public interface IChatMessageService extends IService<ChatMessage> {


    void webAddChatMessage(WebAddChatMessageDto webAddChatMessageDto);


    WebQueryChatMessageVo webQueryChatMessage(Long linkId);

    AppQueryChatMessageVo appQueryChatMessage(Long linkId);

    void appAddChatMessage(AppAddChatMessageDto appAddChatMessageDto);

    void wsAddChatMessage(WsChatMessageDto wsChatMessageDto);
}
