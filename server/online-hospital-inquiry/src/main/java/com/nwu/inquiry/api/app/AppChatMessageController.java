package com.nwu.inquiry.api.app;

import com.nwu.base.model.Result;
import com.nwu.inquiry.model.dto.app.AppAddChatMessageDto;
import com.nwu.inquiry.model.vo.app.AppQueryChatMessageVo;
import com.nwu.inquiry.service.IChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/chat-message")
@Tag(name = "app聊天消息接口")
public class AppChatMessageController {
    @Autowired
    IChatMessageService iChatMessageService;


    @Operation(summary = "获取聊天信息")
    @GetMapping("/query-chat-message")
    public Result<AppQueryChatMessageVo> queryChatMessage(Long linkId) {
        AppQueryChatMessageVo appQueryChatMessageVo = iChatMessageService.appQueryChatMessage(linkId);
        return Result.success(appQueryChatMessageVo);
    }

    @Operation(summary = "发送消息")
    @PostMapping("/send-message")
    public Result<?> sendMessage(@RequestBody AppAddChatMessageDto appAddChatMessageDto) {
        iChatMessageService.appAddChatMessage(appAddChatMessageDto);
        return Result.success();
    }
}
