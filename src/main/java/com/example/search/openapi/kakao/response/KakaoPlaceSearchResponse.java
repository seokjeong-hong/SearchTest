package com.example.search.openapi.kakao.response;

import lombok.Data;

import java.util.List;

@Data
public class KakaoPlaceSearchResponse {
    List<KakaoPlaceSearchDocument> documents;
}
