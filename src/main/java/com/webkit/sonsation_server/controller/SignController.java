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

    @GetMapping("/api/sign")
    public ApiResponse<?> getSigns(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return signService.getAllSigns();
        } else {
            return signService.searchSigns(keyword);
        }
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
