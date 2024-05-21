import request from "@/utils/request";
const INQUIRY_CHAT_LIST_URL = "/inquiry/web/chat-list";
//查询聊天列表
export const inquiryChatList = () => request.get(INQUIRY_CHAT_LIST_URL + "/query-chat-list");
//修改为在线
export const inWindow = linkId => request.put(INQUIRY_CHAT_LIST_URL + "/in-window?linkId=" + `${linkId}`);
//清空未读数
export const clearUnread = linkId => request.put(INQUIRY_CHAT_LIST_URL + "/clear-unread?linkId=" + `${linkId}`);
//修改为下线
export const outWindow = linkId => request.put(INQUIRY_CHAT_LIST_URL + "/out-window?linkId=" + `${linkId}`);
