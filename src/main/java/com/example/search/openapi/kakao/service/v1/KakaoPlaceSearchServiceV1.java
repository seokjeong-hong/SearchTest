package com.example.search.openapi.kakao.service.v1;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;

import java.util.List;

public interface KakaoPlaceSearchServiceV1 {

    List<KakaoPlaceSearchDocument> getPlaceSearchInfo(String keyword);
}
