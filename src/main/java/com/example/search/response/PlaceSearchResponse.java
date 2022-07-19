package com.example.search.response;

import com.example.search.h2.domain.SearchCount;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PlaceSearchResponse {
    List<PlaceSearchItem> places;
    List<SearchCount> keywordTop10;
}
