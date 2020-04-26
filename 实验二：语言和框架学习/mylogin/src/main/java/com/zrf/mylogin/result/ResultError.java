package com.zrf.mylogin.result;

import lombok.Getter;

@Getter
public enum ResultError {
    LOGIN_FAILURE(1, "用户名或密码错误");

    //返回的错误码
    private Integer code;

    //返回的信息
    private String message;

    ResultError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
