package com.mybatis.shoppingmall.common;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "작업 성공"),
    FAILED(500, "작업 실패"),
    VALIDATE_FAILED(404, "페이지 없음"),
    UNAUTHORIZED(401, "로그인되지 않았거나 token이 만료되었습니다"),
    FORBIDDEN(403, "관련 권한 없음");

    private long code;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
