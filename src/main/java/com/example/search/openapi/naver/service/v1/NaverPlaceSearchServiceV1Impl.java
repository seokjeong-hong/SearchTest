package com.example.search.openapi.naver.service.v1;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import com.example.search.openapi.naver.client.NaverPlaceSearchClient;
import com.example.search.openapi.naver.response.NaverPlaceSearchItem;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverPlaceSearchServiceV1Impl implements NaverPlaceSearchServiceV1 {
    private final NaverPlaceSearchClient naverPlaceSearchClient;

    private static final String CLIENT_ID = "bcohvWM678ZKag5AodVZ";
    private static final String CLIENT_SECRET = "GFto3WWpsz";
    private static final int SIZE = 5;

    @Override
    @CircuitBreaker(name = "naver", fallbackMethod = "naverFallback")
    public List<NaverPlaceSearchItem> getPlaceSearchInfo(String keyword) {
        var encodedKeyword = getEncodedString(keyword);
        var naverPlaceSearchResponse = naverPlaceSearchClient.getNaverPlaceSearchResponse(CLIENT_ID,CLIENT_SECRET, encodedKeyword, SIZE);
        if (!HttpStatus.OK.equals(naverPlaceSearchResponse.getStatusCode()) || ObjectUtils.isEmpty(naverPlaceSearchResponse.getBody()))
            return Collections.emptyList();

        return naverPlaceSearchResponse.getBody().getItems();
    }

    private String getEncodedString(String keyword) {
        var bytes = keyword.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private List<KakaoPlaceSearchDocument> naverFallback(String keyword, Exception e) {
        log.info("NaverPlaceSearchServiceV1Impl getPlaceSearchInfo fallback: keyword -", keyword, e.getMessage());
        return Collections.emptyList();
    }
}
