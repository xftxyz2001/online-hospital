package com.nwu.base.model;

import lombok.Data;

import java.io.Serializable;

// 统一返回结果
@Data
public class Result<T> implements Serializable {

    private static final Integer SUCCESS = 1;
    private static final Integer FAILED = 0;

    private Integer code; // 状态码
    private String msg; // 错误信息
    private T data; // 数据

    public static Result<?> success() {
        Result<?> result = new Result<>();
        result.code = SUCCESS;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.code = SUCCESS;
        result.data = object;
        return result;
    }

    public static Result<?> error(String msg) {
        Result<?> result = new Result<>();
        result.code = FAILED;
        result.msg = msg;
        return result;
    }

}