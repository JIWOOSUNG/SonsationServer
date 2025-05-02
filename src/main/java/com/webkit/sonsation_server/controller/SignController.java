package com.webkit.sonsation_server.controller;

import com.webkit.sonsation_server.mapper.SignDetail;
import com.webkit.sonsation_server.mapper.SignListDetail;
import com.webkit.sonsation_server.mapper.SignListItem;
import com.webkit.sonsation_server.response.ApiResponse;
import com.webkit.sonsation_server.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sign")
public class SignController {
    private final SignService signService;

    @GetMapping
    public ApiResponse<List<SignListItem>> getAllSigns(){
        return signService.getAllSigns();
    }

    @GetMapping
    public ApiResponse<List<SignListDetail>> searchSigns(
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ){
        return signService.searchSigns(keyword);
    }

    @GetMapping("/name")
    public ApiResponse<List<String>> getAllSignNames(){
        return signService.getAllSignNames();
    }

    @GetMapping("/{sign_id}")
    public ApiResponse<SignDetail> getSign(
            @PathVariable(value = "sign_id") Long id
    ){
        return signService.getSign(id);
    }
}
