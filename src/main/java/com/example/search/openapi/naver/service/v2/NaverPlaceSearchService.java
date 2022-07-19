package com.example.search.openapi.naver.service.v2;

import com.example.search.response.PlaceSearchItem;

import java.util.List;

public interface NaverPlaceSearchService {
    List<PlaceSearchItem> getPlaceSearchInfo(String keyword);

}
