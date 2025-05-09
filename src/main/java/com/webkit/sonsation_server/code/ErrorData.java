package com.webkit.sonsation_server.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class ErrorData {
    private String message;

    public ErrorData(ErrorCode code, String detail)
    {
        this.message = code.getMessage() + (detail != null ? ": " + detail : "");
    }
}
