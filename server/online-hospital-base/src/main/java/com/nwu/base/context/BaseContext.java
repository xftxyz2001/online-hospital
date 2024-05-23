package com.nwu.base.context;

import com.nwu.base.jwt.UserIdAndIdentity;


public class BaseContext {

    // ThreadLocal为每个线程提供单独一份存储空间，具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问。
    private static final ThreadLocal<UserIdAndIdentity> USER_IDENTITY = new ThreadLocal<>();

    public static UserIdAndIdentity getUserIdentity() {
        return USER_IDENTITY.get();
    }

    public static void setUserIdentity(UserIdAndIdentity userIdentity) {
        USER_IDENTITY.set(userIdentity);
    }

    public static void removeUserIdentity() {
        USER_IDENTITY.remove();
    }

}