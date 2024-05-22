package com.nwu.inquiry.config;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

/**
 * @version v1.0
 * @ClassName: GetHttpSessionConfig
 */
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //获取token
//        Map<String, List<String>> headers = request.getHeaders();
//        String token = headers.get("Sec-WebSocket-Protocol").get(0);
//        System.err.println(headers);
//        System.err.println(token);
//        Claims claims = UserJwtUtils.parseJwt(token);
//        //将token信息存入对象中
//        UserIdentity userIdentity = new UserIdentity();
//        userIdentity.setUserId(Long.valueOf(claims.get("id").toString()));
//        userIdentity.setUserIdentity(Integer.valueOf(claims.get("identity").toString()));
//
//        //将对象保存到socket的session中
//        sec.getUserProperties().put("userIdentity",userIdentity);
    }

}
