package com.nwu.ws;

import com.alibaba.fastjson.JSON;
import com.nwu.base.utils.JwtHelper;
import com.nwu.base.utils.UserIdAndIdentity;
import com.nwu.inquiry.model.ws.WsMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{param}")
@Component
public class ChatEndpoint {

    // 所有用户的Session
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private String userIdentity;

    /**
     * 建立websocket连接后，被调用
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("param") String param) {
        userIdentity = JwtHelper.parseToken(param).toString();
        onlineUsers.put(userIdentity, session);
    }

    /**
     * 浏览器发送消息到服务端，该方法被调用
     * <p>
     * 张三 --> 李四
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        // 心跳
        if (message.equals("heart")) {
            return;
        }
        // 发消息
        WsMessage wsMessage = JSON.parseObject(message, WsMessage.class);
        String toUserIdentity = UserIdAndIdentity.builder()
                .id(wsMessage.getToUserId())
                .identity(wsMessage.getToUserIdentity())
                .build().toString();

        if (onlineUsers.containsKey(toUserIdentity)) {
            onlineUsers.get(toUserIdentity).getAsyncRemote().sendText("update");
        }
    }

    /**
     * 断开 websocket 连接时被调用
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        onlineUsers.remove(userIdentity);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
