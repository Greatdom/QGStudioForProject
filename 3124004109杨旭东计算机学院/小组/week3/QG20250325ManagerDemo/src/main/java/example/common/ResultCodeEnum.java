package com.example.common;

public enum ResultCodeEnum {
    SUCCESS("200", "操作成功"),


    SYSTEM_ERROR("500", "操作错误"),

    ;

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
