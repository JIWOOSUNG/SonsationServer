package com.webkit.sonsation_server.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode implements ApiCode {
    SERVER_ERROR(500000, "서버 내부 오류"),
    BAD_REQUEST(400000, "잘못된 요청"),
    NOT_FOUND(404000, "리소스를 찾을 수 없습니다");

    private final int code;
    private final String message;
}
