package com.example.search.openapi.naver.service.v1;

import com.example.search.openapi.naver.response.NaverPlaceSearchItem;

import java.util.List;

public interface NaverPlaceSearchServiceV1 {
    List<NaverPlaceSearchItem> getPlaceSearchInfo(String keyword);

}
