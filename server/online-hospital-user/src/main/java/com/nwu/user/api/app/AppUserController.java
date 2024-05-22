package com.nwu.user.api.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nwu.base.jwt.JwtHelper;
import com.nwu.base.jwt.UserIdAndIdentity;
import com.nwu.base.model.Result;
import com.nwu.properties.WeChatProperties;
import com.nwu.user.model.dto.user.AppQueryUserInfoVo;
import com.nwu.user.model.dto.user.AppUpdateUserInfoDto;
import com.nwu.user.model.dto.user.UserLoginDto;
import com.nwu.user.model.po.UserInfo;
import com.nwu.user.model.vo.user.QueryUsernameVo;
import com.nwu.user.model.vo.user.UserLoginVo;
import com.nwu.user.service.IUserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/21 0:45
 **/
@RestController
@Tag(name = "App用户接口")
@RequestMapping("/app/user")
public class AppUserController {
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    IUserInfoService iUserInfoService;
    @Autowired
    WeChatProperties weChatProperties;

    @PostMapping("/login")
    @Operation(summary = "用户登录接口")
    public Result<?> userLogin(@RequestBody UserLoginDto userLoginDto) {
        // 发送微信登录请求

        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", userLoginDto.getCode());
        map.put("grant_type", "authorization_code");
        String json = restTemplate.getForObject(WX_LOGIN, String.class, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        if (openid == null)
            return Result.error("登陆失败");

        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setIsFirst(0);// 不是第一次登录
        // 判断是否第一次登录
        UserInfo userInfo = iUserInfoService.isFirstLogin(openid);
        if (userInfo == null) {
            // 第一次登录
            userInfo = iUserInfoService.appUserLogin(openid);
            userLoginVo.setIsFirst(1);
        }

        // 生成jwt令牌
        String token = JwtHelper.generateToken(UserIdAndIdentity.builder().id(userInfo.getId()).identity(0).build());
        userLoginVo.setToken(token);
        return Result.success(userLoginVo);
    }

    @GetMapping("/queryUsername")
    @Operation(summary = "查询用户名接口")
    public Result<QueryUsernameVo> getUsername() {
        String username = iUserInfoService.getUsername();
        QueryUsernameVo queryUsernameVo = QueryUsernameVo.builder().username(username).build();
        return Result.success(queryUsernameVo);
    }

    @GetMapping("/queryUserInfo")
    @Operation(summary = "查询用户信息接口")
    public Result<AppQueryUserInfoVo> getUserInfo() {
        AppQueryUserInfoVo appQueryUserInfoVo = iUserInfoService.appQueryUserInfo();
        return Result.success(appQueryUserInfoVo);
    }

    @PutMapping("/updateUserInfo")
    @Operation(summary = "修改用户信息")
    public Result<?> updateUserInfo(@RequestBody AppUpdateUserInfoDto appUpdateUserInfoDto) {
        iUserInfoService.appUpdateUserInfo(appUpdateUserInfoDto);
        return Result.success();
    }

}
