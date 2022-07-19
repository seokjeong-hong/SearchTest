package com.example.search.service;

import com.example.search.response.PlaceSearchResponse;

public interface PlaceSearchService {
    PlaceSearchResponse getPlaceSearchResponse(String keyword);
    void insertOrUpdateSearchCount(String keyword);
}
