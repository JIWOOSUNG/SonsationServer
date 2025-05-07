package com.webkit.sonsation_server.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class SignListDetail {
    private String name;
    private String url;

    public static SignListDetail toListDetail(String name, String url){
        return SignListDetail.builder()
                .name(name)
                .url(url)
                .build();
    }
}
