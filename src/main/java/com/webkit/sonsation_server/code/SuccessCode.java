package com.webkit.sonsation_server.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessCode implements ApiCode{
    Ok(200000), Empty(2000001);

    private final int code;
}
