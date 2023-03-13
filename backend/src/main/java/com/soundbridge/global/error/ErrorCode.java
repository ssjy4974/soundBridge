package com.soundbridge.global.error;

public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "valid check."),
    ALREADY_EXIST_RESOURCE(400, "already exist resource."),
    LOGIN_REQUIRED(401, "login required."),
    EXPIRED_ACCESSTOKEN(401, "expired access-token"),
    NOT_AUTHENTICATION(401, "not authentication"),
    NOT_AUTHORIZATION(403, "not authorization"),
    MEMBER_NOT_FOUND(404, "member not found."),
    MY_SENTENCE_LOG_NOT_FOUND(404, "mySentenceLog not found."),
    BASIC_LETTER_NOT_FOUND(404, "basicLetter not found."),
    DAILY_WORD_NOT_FOUND(404, "dailyWord not found,"),
    TRY_HISTORY_NOT_FOUND(404, "tryHistory not found."),
    VOICE_NOT_FOUND(404, "voice not found."),
    QUICK_SENTENCE(404, "quickSentence not found."),
    FEEDBACK_BOARD_NOT_FOUND(404, "feedBackBoard not found."),
    MEETING_NOT_FOUND(404, "meeting not found."),
    MEETING_ROOM_NOT_FOUND(404, "meetingRoom not found."),
    CATEGORY_NOT_FOUND(404, "category not found."),
    FEATURE_NOT_FOUND(404, "feature not found."),
    EXCEED_FILE_SIZE(413, "file size can't exceed 2MB"),
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