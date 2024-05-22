package com.nwu.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/4 2:16
 **/
@Data
public class Result<T> implements Serializable {

    private static final Integer SUCCESS = 1;
    private static final Integer FAILED = 0;

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

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