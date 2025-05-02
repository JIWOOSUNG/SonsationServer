package com.webkit.sonsation_server.service;

import com.webkit.sonsation_server.mapper.SignDetail;
import com.webkit.sonsation_server.mapper.SignListDetail;
import com.webkit.sonsation_server.mapper.SignListItem;
import com.webkit.sonsation_server.repository.CategoryRepository;
import com.webkit.sonsation_server.repository.SignRepository;
import com.webkit.sonsation_server.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignService {
    private final CategoryRepository categoryRepository;
    private final SignRepository signRepository;


    public ApiResponse<List<SignListItem>> getAllSigns() {
        return null;
    }

    public ApiResponse<List<SignListDetail>> searchSigns(String keyword) {
        return null;
    }

    public ApiResponse<List<String>> getAllSignNames() {
        return null;
    }

    public ApiResponse<SignDetail> getSign(Long id) {
        return null;
    }
}
