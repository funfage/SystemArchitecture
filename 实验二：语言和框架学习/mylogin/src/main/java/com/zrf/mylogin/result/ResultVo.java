package com.zrf.mylogin.result;

import lombok.Getter;

@Getter
public class ResultVo {

    private Integer code;

    private Boolean success;

    private Object data;

    private String message;

    public static ResultVo success(Object data) {
        return new ResultVo(0, true, data, "success");
    }

    public static ResultVo failure(ResultError error) {
        return new ResultVo(error.getCode(), false, error.getMessage());
    }

    public ResultVo(Integer code, Boolean success, Object data, String msg) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = msg;
    }


    public ResultVo(Integer code, Boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.message = msg;
    }


    @Override
    public String toString() {
        return "ResultVo{" +
                "code=" + code +
                ", success=" + success +
                ", data=" + data +
                ", msg='" + message + '\'' +
                '}';
    }
}
