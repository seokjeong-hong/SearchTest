package com.example.search.openapi.kakao.service.v1;

import com.example.search.openapi.kakao.client.KakaoPlaceSearchClient;
import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import com.example.search.openapi.kakao.response.KakaoPlaceSearchResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPlaceSearchServiceV1Impl implements KakaoPlaceSearchServiceV1 {

    private final KakaoPlaceSearchClient kakaoPlaceSearchClient;

    private static final String REST_API_KEY = "KakaoAK 58c1c91b36d83f82ea5b304d91efa1a6";
    private static final int SIZE = 5;
    @Override
    @CircuitBreaker(name = "kakao", fallbackMethod = "kakaoFallback")
    public List<KakaoPlaceSearchDocument> getPlaceSearchInfo(String keyword) {
        ResponseEntity<KakaoPlaceSearchResponse> kakaoPlaceSearchResponse = kakaoPlaceSearchClient.getKakaoPlaceSearchResponse(REST_API_KEY, keyword, SIZE);
        return kakaoPlaceSearchResponse.getBody().getDocuments();
    }

    private List<KakaoPlaceSearchDocument> kakaoFallback(String keyword, Exception e) {
        return Collections.emptyList();
    }
}
