package com.nwu.base.context;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/23 15:52
 **/
//ThreadLocal为每个线程提供单独一份存储空间，具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问。
//用来解决保存当前用户ID
public class BaseContext {

    public static ThreadLocal<Long> threadLocalId = new ThreadLocal<>();
    public static ThreadLocal<Integer> threadLocalIdentity = new ThreadLocal<>();

    public static Long getCurrentId() {
        return threadLocalId.get();
    }

    public static void setCurrentId(Long id) {
        threadLocalId.set(id);
    }

    public static Integer getCurrentIdentity() {
        return threadLocalIdentity.get();
    }

    public static void setCurrentIdentity(Integer id) {
        threadLocalIdentity.set(id);
    }

    public static void removeCurrentId() {
        threadLocalId.remove();
    }

}