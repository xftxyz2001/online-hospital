package com.nwu.inquiry.api.web;


import com.nwu.base.model.Result;
import com.nwu.inquiry.model.vo.web.WebQueryChatListVo;
import com.nwu.inquiry.service.IChatListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("web/chat-list")
@Tag(name = "web聊天列表接口")
public class WebChatListController {
    @Autowired
    IChatListService iChatListService;

    @Operation(summary = "获取聊天列表")
    @GetMapping("/query-chat-list")
    public Result<List<WebQueryChatListVo>> queryChatList() {
        List<WebQueryChatListVo> webQueryChatListVos = iChatListService.webQueryChatList();
        return Result.success(webQueryChatListVos);
    }

    @Operation(summary = "设置上线")
    @PutMapping("/in-window")
    public Result<?> inWindow(Long linkId) {
        iChatListService.webInWindow(linkId);
        return Result.success();
    }

    @Operation(summary = "设置下线")
    @PutMapping("/out-window")
    public Result<?> outWindow(Long linkId) {
        iChatListService.webOutWindow(linkId);
        return Result.success();
    }

    @Operation(summary = "未读数设为0")
    @PutMapping("/clear-unread")
    public Result<?> clearUnread(Long linkId) {
        iChatListService.webClearUnread(linkId);
        return Result.success();
    }

}
