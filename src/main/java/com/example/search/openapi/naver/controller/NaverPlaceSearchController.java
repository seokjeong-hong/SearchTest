package com.example.search.openapi.naver.controller;

import com.example.search.openapi.naver.response.NaverPlaceSearchItem;
import com.example.search.openapi.naver.service.v1.NaverPlaceSearchServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NaverPlaceSearchController {

    private final NaverPlaceSearchServiceV1 naverPlaceSearchServiceV1;

    @GetMapping("/v1/naver/place")
    private List<NaverPlaceSearchItem> search(@RequestParam(name = "query") String keyword) {
        return naverPlaceSearchServiceV1.getPlaceSearchInfo(keyword);
    }
}
