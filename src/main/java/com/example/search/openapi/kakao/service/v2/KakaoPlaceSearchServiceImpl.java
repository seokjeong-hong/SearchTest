package com.example.search.openapi.kakao.service.v2;

import com.example.search.openapi.kakao.client.KakaoPlaceSearchClient;
import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import com.example.search.response.PlaceSearchItem;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoPlaceSearchServiceImpl implements KakaoPlaceSearchService{

    private final KakaoPlaceSearchClient kakaoPlaceSearchClient;

    private static final String REST_API_KEY = "KakaoAK 58c1c91b36d83f82ea5b304d91efa1a6";
    private static final int SIZE = 10;

    @Override
    @CircuitBreaker(name = "kakao", fallbackMethod = "kakaoFallback")
    public List<PlaceSearchItem> getPlaceSearchInfo(String keyword) {
        var kakaoPlaceSearchResponse = kakaoPlaceSearchClient.getKakaoPlaceSearchResponse(REST_API_KEY, keyword, SIZE);
        var kakaoPlaceSearchDocuments = kakaoPlaceSearchResponse.getBody().getDocuments();

        return kakaoPlaceSearchDocuments.stream().map(kakao -> {
            var placeName = kakao.getPlaceName();
            return PlaceSearchItem.of(placeName);
        }).toList();
    }

    private List<KakaoPlaceSearchDocument> kakaoFallback(String keyword, Exception e) {
        log.info("KakaoPlaceSearchServiceImpl getPlaceSearchInfo fallback: keyword -", keyword, e.getMessage());
        return Collections.emptyList();
    }
}
