package com.nwu.ws;

import com.alibaba.fastjson.JSON;
import com.nwu.base.utils.JwtHelper;
import com.nwu.base.utils.JwtHelper.UserInfo;
import com.nwu.config.GetHttpSessionConfig;
import com.nwu.inquiry.model.ws.UserIdentity;
import com.nwu.inquiry.model.ws.WsMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{param}", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    // 所有用户的Session
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private UserIdentity userIdentity;

    /**
     * 建立websocket连接后，被调用
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("param") String param) {
        // 1.获取到传来的对象
        System.err.println(param);
        UserInfo userInfo = JwtHelper.parseToken(param);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(userInfo.getId());
        userIdentity.setUserIdentity(userInfo.getIdentity());
        this.userIdentity = userIdentity;
        // 2.将对象的字符串格式和websocket的session作为键值对存入onlineUsers中
        onlineUsers.put(this.userIdentity.toString(), session);
        // System.err.println(this.userIdentity.toString());
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
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserIdentity(wsMessage.getToUserIdentity());
        userIdentity.setUserId(wsMessage.getToUserId());

        if (onlineUsers.containsKey(userIdentity.toString())) {
            System.err.println("send");
            Session toSession = onlineUsers.get(userIdentity.toString());
            toSession.getAsyncRemote().sendText("update");
        }
    }

    /**
     * 断开 websocket 连接时被调用
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        onlineUsers.remove(this.userIdentity.toString());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
