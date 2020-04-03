package com.alan.changesettingdemo.core;

/**
 * Created by tiny on 2/25/20.
 */
public class NetModel<M> {
    private int code = -1;
    private String message;
    private M data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }
}
