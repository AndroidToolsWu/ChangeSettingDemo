package com.alan.changesettingdemo.core;

import java.io.IOException;

/**
 * Created by tiny on 12/13/2018.
 */
public class HttpServerException extends IOException {

    private int code;

    public HttpServerException(int code, String result) {
        super(result);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
