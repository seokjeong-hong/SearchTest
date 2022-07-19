package com.example.search.openapi.naver.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NaverPlaceSearchResponse {

    int total;

    int start;

    int display;

    List<NaverPlaceSearchItem> items;
}
