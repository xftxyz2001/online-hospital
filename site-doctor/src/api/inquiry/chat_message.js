import request from "@/utils/request";
const INQUIRY_CHAT_MESSAGE_URL = "/inquiry/web/chat-message";
//查询聊天列表
export const inquiryChatMessage = linkId =>
  request.get(INQUIRY_CHAT_MESSAGE_URL + "/query-chat-message?linkId=" + `${linkId}`);
//发送消息
export const sendMessage = addChatMessageDto =>
  request.post(INQUIRY_CHAT_MESSAGE_URL + "/send-message", addChatMessageDto);
