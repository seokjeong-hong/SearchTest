package com.example.search.openapi.naver.service.v2;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import com.example.search.openapi.naver.client.NaverPlaceSearchClient;
import com.example.search.response.PlaceSearchItem;
import com.example.search.util.HtmlTagUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverPlaceSearchServiceImpl implements NaverPlaceSearchService {
    private final NaverPlaceSearchClient naverPlaceSearchClient;

    private static final String CLIENT_ID = "bcohvWM678ZKag5AodVZ";
    private static final String CLIENT_SECRET = "GFto3WWpsz";
    private static final int SIZE = 5;

    @Override
    @CircuitBreaker(name = "naver", fallbackMethod = "naverFallback")
    public List<PlaceSearchItem> getPlaceSearchInfo(String keyword) {
        var encodedKeyword = getEncodedString(keyword);
        var naverPlaceSearchResponse = naverPlaceSearchClient.getNaverPlaceSearchResponse(CLIENT_ID,CLIENT_SECRET, encodedKeyword, SIZE);
        var naverPlaceSearchItems = naverPlaceSearchResponse.getBody().getItems();

        return naverPlaceSearchItems.stream().map(naver -> {
            var title = HtmlTagUtil.removeHtmlTag(naver.getTitle());
            return PlaceSearchItem.of(title);
        }).toList();
    }

    private String getEncodedString(String keyword) {
        var bytes = keyword.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private List<KakaoPlaceSearchDocument> naverFallback(String keyword, Exception e) {
        log.info("NaverPlaceSearchServiceImpl getPlaceSearchInfo fallback: keyword -", keyword, e.getMessage());
        return Collections.emptyList();
    }
}
