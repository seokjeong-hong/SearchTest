package com.example.search.openapi.kakao.controller;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import com.example.search.openapi.kakao.service.v1.KakaoPlaceSearchServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoPlaceSearchController {

    private final KakaoPlaceSearchServiceV1 kakaoPlaceSearchServiceV1;

    @GetMapping("/v1/kakao/place")
    private List<KakaoPlaceSearchDocument> serach(@RequestParam(name = "q") String keyword) {
        return kakaoPlaceSearchServiceV1.getPlaceSearchInfo(keyword);
    }
}
