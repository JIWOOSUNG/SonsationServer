package com.webkit.sonsation_server.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode implements ApiCode{
    SERVER_ERROR(500000);

    // TODO : 잘못된 요청 (400) 처리 유무 논의

    private final int code;
}
