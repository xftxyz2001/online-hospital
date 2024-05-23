package com.nwu.user.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwu.user.autoconfigure.WeChatProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RequiredArgsConstructor
public class WeChatTemplate {

    // https://developers.weixin.qq.com/minigame/dev/api-backend/open-api/login/auth.code2Session.html
    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    private final WeChatProperties weChatProperties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public String getOpenId(String code) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(WX_LOGIN)
                .queryParam("appid", weChatProperties.getAppid())
                .queryParam("secret", weChatProperties.getSecret())
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code");

        String json = restTemplate.getForObject(builder.toUriString(), String.class);
        Map<String, String> jsonObject = null;
        try {
            jsonObject = objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            return null;
        }
        return jsonObject.get("openid");

    }
}
