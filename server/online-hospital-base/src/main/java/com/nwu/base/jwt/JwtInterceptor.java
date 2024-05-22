package com.nwu.base.jwt;

import com.nwu.base.context.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    // token对应的httpHeader的key
    public static final String TOKEN_HEADER_NAME = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 非动态方法，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader(TOKEN_HEADER_NAME);
        try {
            UserIdAndIdentity userIdAndIdentity = JwtHelper.parseToken(token);
            BaseContext.setUserIdentity(userIdAndIdentity);
            log.info("用户登录成功，当前用户的id：{}", userIdAndIdentity.getId());
            return true;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeUserIdentity();
    }
}
