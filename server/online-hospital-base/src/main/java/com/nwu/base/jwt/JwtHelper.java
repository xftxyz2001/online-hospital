package com.nwu.base.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtHelper {
    public static final String X_ID = "id";
    public static final String X_IDENTITY = "identity";
    // 密钥
    private static final String SECRET_KEY = "secret-key";
    // 过期时间
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000; // 12小时

    // 生成token
    public static String generateToken(UserIdAndIdentity userIdAndIdentity) {
        return JWT.create()
                .withClaim(X_ID, userIdAndIdentity.getId())
                .withClaim(X_IDENTITY, userIdAndIdentity.getIdentity())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // 解析token
    public static UserIdAndIdentity parseToken(String token) {
        DecodedJWT verifyed = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return UserIdAndIdentity.builder()
                .id(verifyed.getClaim(X_ID).asLong())
                .identity(verifyed.getClaim(X_IDENTITY).asInt())
                .build();
    }

}
