package com.example.search.openapi.kakao.service.v2;

import com.example.search.response.PlaceSearchItem;

import java.util.List;

public interface KakaoPlaceSearchService {

    List<PlaceSearchItem> getPlaceSearchInfo(String keyword);
}
