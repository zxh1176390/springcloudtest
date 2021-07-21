package com.zxh.common;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:39
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code = 200;
    private String message = "";
    private T data;

    public Result<T> failure(String message) {
        return new Result(500, message);
    }

    public Result<T> fail(String message) {
        return new Result(500, message);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data) {
        this.data = data;
    }

    public Result() {
    }
}
