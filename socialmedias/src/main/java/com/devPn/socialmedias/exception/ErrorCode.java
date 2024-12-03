package com.devPn.socialmedias.exception;

public enum ErrorCode {

    UNCATEGORIZED_ERROR(9999 ,"Uncategorized error"),
    USER_NOT_EXITS(1002, " Account not exits!!"),
    MULTIPLE_USERS_FOUND(1003, "Multiple users found"),
    UNAUTHENTICATED (1004, "Unauthenticated"),
    USER_EXIST(1001 ,"Email exist!!"),
    TOKEN_NOT_FOUND (1005, "Token not found "),

    IS_ENABLE(1005, "Account not verified, please check email to verify account!!"),

    EMAIL_ALREADY_CONFIRM (1006, "Email already confirm "),

    IS_FOLLOWING (1000, "User has followed!!"),

    ROOM_NOT_FOUND (1, "Room not found!!"),

    ROOM_EXISTING (1, "Room is existing "),

    POST_NOT_FOUND (1, "Post not found"),
    POST_IMAGE_NOT_FOUND (1, "Post image not found!"),

    NO_RECEIVERS_PROVIDED(1, "No receivers provided"),

    INVALID_USER_ID(1, "Invalid userId"),


    NOT_FOUND_FOLLOW(1, "Not found follow"),


    SIZE_MEMBER(1, "Number of members is less than 2 ")










    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
