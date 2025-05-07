package com.webkit.sonsation_server.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignDetail {
    private String name;
    private String description;
    private String url;

    // Sign -> SignDetail.json
    public static SignDetail toDetail(String name, String description, String url){
        return SignDetail.builder()
                .name(name)
                .description(description)
                .url(url)
                .build();
    }
}
