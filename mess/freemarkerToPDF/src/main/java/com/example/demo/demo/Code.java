package com.example.demo.demo;


import lombok.AllArgsConstructor;
import lombok.Data;


public enum Code {

    FAIL("200","fail"),
    DATA("200","data","我是数据"),
    SUCESS("400","sceff");


    private String code;

    private String msg;

    private String data;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    Code(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
