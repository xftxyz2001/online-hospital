package com.nwu.base.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.Builder;
import lombok.Data;

public class JwtHelper {
    // 密钥
    private static final String SECRET_KEY = "secret-key";
    // 过期时间
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000; // 12小时

    public static final String X_ID = "id";
    public static final String X_IDENTITY = "identity";

    @Data
    @Builder
    public static class UserInfo {
        private Long id;
        private Integer identity;
    }

    // 生成token
    public static String generateToken(UserInfo userInfo) {
        return JWT.create()
                .withClaim(X_ID, userInfo.getId())
                .withClaim(X_IDENTITY, userInfo.getIdentity())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // 解析token
    public static UserInfo parseToken(String token) {
        DecodedJWT verifyed = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return UserInfo.builder()
                .id(verifyed.getClaim(X_ID).asLong())
                .identity(verifyed.getClaim(X_IDENTITY).asInt())
                .build();
    }

}
