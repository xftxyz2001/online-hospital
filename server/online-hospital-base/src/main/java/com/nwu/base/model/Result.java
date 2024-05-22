package com.nwu.base.model;

import com.nwu.base.constant.ResultCodeConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author lcy
 * @DateTime 2024/3/4 2:16
 **/
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = ResultCodeConstant.RESULT_SUCCESS;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = ResultCodeConstant.RESULT_SUCCESS;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = ResultCodeConstant.RESULT_FAILED;
        return result;
    }

}