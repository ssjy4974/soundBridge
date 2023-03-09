package com.soundbridge.global.error;

public enum ErrorCode {

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}