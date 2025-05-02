package com.webkit.sonsation_server.mapper;


public class SignDetail {
    private String name;
    private String description;
    private String url;

    // Sign -> SignDetail.json
    public static SignDetail toDetail(){
        return new SignDetail();
    }
}
