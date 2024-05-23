package com.nwu.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwu.user.autoconfigure.WeChatProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class WeChatTemplate {

    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    private final WeChatProperties weChatProperties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public String getOpenId(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = restTemplate.getForObject(WX_LOGIN, String.class, map);
        Map<String, String> jsonObject = null;
        try {
            jsonObject = objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            return null;
        }
        return jsonObject.get("openid");

    }
}
