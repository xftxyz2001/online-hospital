package com.nwu.inquiry.api.web;


import com.nwu.base.model.Result;
import com.nwu.inquiry.model.dto.web.WebAddChatMessageDto;
import com.nwu.inquiry.model.vo.web.WebQueryChatMessageVo;
import com.nwu.inquiry.service.IChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 聊天内容详情表 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-04-08
 */
@RestController
@RequestMapping("web/chat-message")
@Tag(name = "web聊天消息接口")
public class WebChatMessageController {

    @Autowired
    IChatMessageService iChatMessageService;


    @Operation(summary = "获取聊天信息")
    @GetMapping("/query-chat-message")
    public Result<WebQueryChatMessageVo> queryChatMessage(Long linkId) {
        WebQueryChatMessageVo webQueryChatMessageVo = iChatMessageService.webQueryChatMessage(linkId);
        return Result.success(webQueryChatMessageVo);
    }

    @Operation(summary = "发送消息")
    @PostMapping("/send-message")
    public Result<?> sendMessage(@RequestBody WebAddChatMessageDto webAddChatMessageDto) {
        iChatMessageService.webAddChatMessage(webAddChatMessageDto);
        return Result.success();
    }


}
