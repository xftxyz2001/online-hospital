package com.nwu.inquiry.api.web;


import com.nwu.base.model.Result;
import com.nwu.inquiry.model.vo.web.WebQueryChatListVo;
import com.nwu.inquiry.service.IChatListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 聊天列表表 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
@RestController
@RequestMapping("web/chat-list")
@Api(tags = "web聊天列表接口")
public class WebChatListController {
    @Autowired
    IChatListService iChatListService;

    @ApiOperation(value = "获取聊天列表")
    @GetMapping("/query-chat-list")
    public Result<List<WebQueryChatListVo>> queryChatList() {
        List<WebQueryChatListVo> webQueryChatListVos = iChatListService.webQueryChatList();
        return Result.success(webQueryChatListVos);
    }

    @ApiOperation(value = "设置上线")
    @PutMapping("/in-window")
    public Result inWindow(Long linkId) {
        iChatListService.webInWindow(linkId);
        return Result.success();
    }

    @ApiOperation(value = "设置下线")
    @PutMapping("/out-window")
    public Result outWindow(Long linkId) {
        iChatListService.webOutWindow(linkId);
        return Result.success();
    }

    @ApiOperation(value = "未读数设为0")
    @PutMapping("/clear-unread")
    public Result clearUnread(Long linkId) {
        iChatListService.webClearUnread(linkId);
        return Result.success();
    }

}
