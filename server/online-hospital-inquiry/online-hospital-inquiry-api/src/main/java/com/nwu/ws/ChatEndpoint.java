package com.nwu.ws;

import com.alibaba.fastjson.JSON;
import com.nwu.base.utils.UserJwtUtils;
import com.nwu.config.GetHttpSessionConfig;
import com.nwu.inquiry.model.ws.UserIdentity;
import com.nwu.inquiry.model.ws.WsMessage;
import com.nwu.inquiry.service.IChatListService;
import io.jsonwebtoken.Claims;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version v1.0
 * @ClassName: ChatEndpoint
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
@ServerEndpoint(value = "/chat/{param}", configurator = GetHttpSessionConfig.class)
@Component
public class ChatEndpoint {

    //所有用户的Session
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    private UserIdentity userIdentity;

    //当前用户的session
    private Session session;
    private IChatListService iChatListService;

    /**
     * 建立websocket连接后，被调用
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("param") String param) {
        //1.获取到传来的对象
        System.err.println(param);
        Claims claims = UserJwtUtils.parseJwt(param);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(Long.valueOf(claims.get("id").toString()));
        userIdentity.setUserIdentity(Integer.valueOf(claims.get("identity").toString()));
        this.userIdentity = userIdentity;
        //2.将对象的字符串格式和websocket的session作为键值对存入onlineUsers中
        onlineUsers.put(this.userIdentity.toString(), session);
//        System.err.println(this.userIdentity.toString());
//       // 保存session
        this.session = session;
    }


    /**
     * 浏览器发送消息到服务端，该方法被调用
     * <p>
     * 张三  -->  李四
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {

        //心跳
        if (message.equals("heart")) {

            return;
        }
        //发消息
        WsMessage wsMessage = JSON.parseObject(message, WsMessage.class);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserIdentity(wsMessage.getToUserIdentity());
        userIdentity.setUserId(wsMessage.getToUserId());


        if (onlineUsers.containsKey(userIdentity.toString())) {
            System.err.println("send");
            Session toSession = onlineUsers.get(userIdentity.toString());
            toSession.getAsyncRemote().sendText("update");
        }

//        try {
//            //将消息推送给指定的用户
//            Message msg = JSON.parseObject(message, Message.class);
//            //获取 消息接收方的用户名
//            String toName = msg.getToName();
//            String mess = msg.getMessage();
//            //获取消息接收方用户对象的session对象
//            Session session = onlineUsers.get(toName);
//            String user = (String) this.httpSession.getAttribute("user");
//            String msg1 = MessageUtils.getMessage(false, user, mess);
//            session.getBasicRemote().sendText(msg1);
//        } catch (Exception e) {
//            //记录日志
//        }
    }

    /**
     * 断开 websocket 连接时被调用
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
//        //1,从onlineUsers中剔除当前用户的session对象
//        String user = (String) this.httpSession.getAttribute("user");
//        onlineUsers.remove(user);
//        //2,通知其他所有的用户，当前用户下线了
//        String message = MessageUtils.getMessage(true,null,getFriends());
//        broadcastAllUsers(message);


//        System.err.println("断开："+this.userIdentity.toString());
//        onlineUsers.remove(this.userIdentity.toString());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
