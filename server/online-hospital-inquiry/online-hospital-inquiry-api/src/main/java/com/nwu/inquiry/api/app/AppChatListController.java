package com.nwu.inquiry.api.app;

import com.nwu.base.model.Result;
import com.nwu.inquiry.model.vo.app.AppQueryChatListVo;
import com.nwu.inquiry.model.vo.app.UnreadNumber;
import com.nwu.inquiry.service.IChatListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/chat-list")
@Api(tags = "app聊天列表接口")
public class AppChatListController {
    @Autowired
    IChatListService iChatListService;

    @ApiOperation(value = "获取聊天列表")
    @GetMapping("/query-chat-list")
    public Result<List<AppQueryChatListVo>> queryChatList() {
        List<AppQueryChatListVo> appQueryChatListVos = iChatListService.appQueryChatList();
        return Result.success(appQueryChatListVos);
    }

    @ApiOperation(value = "设置上线")
    @PutMapping("/in-window")
    public Result<?> inWindow(Long linkId) {
        iChatListService.appInWindow(linkId);
        return Result.success();
    }

    @ApiOperation(value = "设置下线")
    @PutMapping("/out-window")
    public Result<?> outWindow(Long linkId) {
        iChatListService.appOutWindow(linkId);
        return Result.success();
    }

    @ApiOperation(value = "未读数设为0")
    @PutMapping("/clear-unread")
    public Result<?> clearUnread(Long linkId) {
        iChatListService.appClearUnread(linkId);
        return Result.success();
    }

    @ApiOperation(value = "查询所有未读数")
    @GetMapping("/allUnread")
    public Result<UnreadNumber> queryAllUnreadNo() {
        UnreadNumber unreadNumber = iChatListService.appQueryAllUnreadNo();
        return Result.success(unreadNumber);
    }
}
