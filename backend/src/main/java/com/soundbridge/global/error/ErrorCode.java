package com.soundbridge.global.error;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "valid check."),
    LOGIN_REQUIRED(401, "login required"),
    EXPIRED_ACCESSTOKEN(401, "expired access-token"),
    NOT_AUTHENTICATION(401, "not authentication"),
    NOT_AUTHORIZATION(403, "not authorization"),
    MEMBER_NOT_FOUND(404, "member not found"),
    EXCEED_FILE_SIZE(413, "file size can't exceed 2MB"),
    EXTENSION_NOT_ALLOWED(415, "extension not allowed."),
    CATEGORY_NOT_FOUND(404, "category not found"),
    MEETING_ROOM_NOT_FOUND(404, "meetingRoom not found.")
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