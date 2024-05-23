package com.nwu.inquiry.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwu.base.jwt.JwtHelper;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.inquiry.model.ws.WsMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{param}")
@Component
public class ChatEndpoint {

    // 所有用户的Session
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    private static ObjectMapper objectMapper;
    private String userIdentity;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        ChatEndpoint.objectMapper = objectMapper;
    }

    // 连接建立
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("param") String param) {
        userIdentity = JwtHelper.parseToken(param).toString();
        onlineUsers.put(userIdentity, session);
    }

    // 收到消息
    @OnMessage
    @SneakyThrows
    public void onMessage(String message) {
        // 心跳
        if (message.equals("heart")) {
            return;
        }
        // 发消息
        WsMessage wsMessage = objectMapper.readValue(message, WsMessage.class);
        String toUserIdentity = UserIdAndIdentity.builder()
                .id(wsMessage.getToUserId())
                .identity(wsMessage.getToUserIdentity())
                .build().toString();

        if (onlineUsers.containsKey(toUserIdentity)) {
            onlineUsers.get(toUserIdentity).getAsyncRemote().sendText("update");
        }
    }

    // 连接关闭
    @OnClose
    public void onClose(Session session) {
        onlineUsers.remove(userIdentity);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
