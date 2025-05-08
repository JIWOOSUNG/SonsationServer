package com.webkit.sonsation_server.mapper;

import com.webkit.sonsation_server.model.Sign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class QuizListItem {
    private Long id;
    private String name;
    // private String url;

    public static QuizListItem toListItem(Sign sign){
        return QuizListItem.builder()
                .id(sign.getId())
                .name(sign.getName())
                // .url(sign.getUrl())
                .build();
    }
}
