package com.webkit.sonsation_server.controller;

import com.webkit.sonsation_server.code.SuccessCode;
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
    public ApiResponse<?> getSigns(@RequestParam(required = false) String keyword) {
        try {
            List<?> listItems =  keyword == null || keyword.isBlank()
                    ? signService.getAllSigns()
                    : signService.searchSigns(keyword);

            return ApiResponse.success(
                    listItems.isEmpty()
                            ? SuccessCode.DATA_EMPTY
                            : SuccessCode.OK,
                    listItems
            );
        } catch (Exception e) {
            // TODO : 에러 발생 시 ApiResponse.fail()로 반환
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/name")
    public ApiResponse<List<String>> getAllSignNames(){
        try {
            List<String> signNames = signService.getAllSignNames();

            return ApiResponse.success(SuccessCode.OK, signNames);
        } catch (Exception e) {
            // TODO : 에러 발생 시 ApiResponse.fail()로 반환
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{sign_id}")
    public ApiResponse<SignDetail> getSign(
            @PathVariable(value = "sign_id") Long id
    ){
        try {
            SignDetail signDetail = signService.getSign(id);

            return ApiResponse.success(SuccessCode.OK, signDetail);
        } catch (Exception e) {
            // TODO : 에러 발생 시 ApiResponse.fail()로 반환
            throw new RuntimeException(e);
        }
    }
}
