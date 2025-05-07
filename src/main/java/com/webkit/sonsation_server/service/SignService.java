package com.webkit.sonsation_server.service;

import com.webkit.sonsation_server.mapper.SignDetail;
import com.webkit.sonsation_server.mapper.SignListDetail;
import com.webkit.sonsation_server.mapper.SignListItem;
import com.webkit.sonsation_server.model.Category;
import com.webkit.sonsation_server.repository.CategoryRepository;
import com.webkit.sonsation_server.repository.SignRepository;
import com.webkit.sonsation_server.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService {
    private final CategoryRepository categoryRepository;
    private final SignRepository signRepository;


    public List<SignListItem> getAllSigns() {
        List<Category> categories = categoryRepository.findAll();
        List<SignListDetail> items = new ArrayList<>();
        List<SignListItem> signListItems = new ArrayList<>();



        return null;
    }

    public List<SignListDetail> searchSigns(String keyword) {
        return null;
    }

    public List<String> getAllSignNames() {
        return null;
    }

    public SignDetail getSign(Long id) {
        return null;
    }
}
