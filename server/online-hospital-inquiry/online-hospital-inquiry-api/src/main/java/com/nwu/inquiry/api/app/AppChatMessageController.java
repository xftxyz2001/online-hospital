package com.nwu.inquiry.api.app;

import com.nwu.base.model.Result;
import com.nwu.inquiry.model.dto.app.AppAddChatMessageDto;
import com.nwu.inquiry.model.vo.app.AppQueryChatMessageVo;
import com.nwu.inquiry.service.IChatMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/chat-message")
@Api(tags = "app聊天消息接口")
public class AppChatMessageController {
    @Autowired
    IChatMessageService iChatMessageService;


    @ApiOperation(value = "获取聊天信息")
    @GetMapping("/query-chat-message")
    public Result<AppQueryChatMessageVo> queryChatMessage(Long linkId) {
        AppQueryChatMessageVo appQueryChatMessageVo = iChatMessageService.appQueryChatMessage(linkId);
        return Result.success(appQueryChatMessageVo);
    }

    @ApiOperation(value = "发送消息")
    @PostMapping("/send-message")
    public Result sendMessage(@RequestBody AppAddChatMessageDto appAddChatMessageDto) {
        iChatMessageService.appAddChatMessage(appAddChatMessageDto);
        return Result.success();
    }
}
