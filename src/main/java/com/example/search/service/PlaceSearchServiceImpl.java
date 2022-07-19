package com.example.search.service;

import com.example.search.h2.domain.SearchCount;
import com.example.search.h2.repository.SearchCountRepository;
import com.example.search.openapi.kakao.service.v2.KakaoPlaceSearchService;
import com.example.search.openapi.naver.service.v2.NaverPlaceSearchService;
import com.example.search.response.PlaceSearchItem;
import com.example.search.response.PlaceSearchResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceSearchServiceImpl implements PlaceSearchService {

    private final KakaoPlaceSearchService kakaoPlaceSearchService;
    private final NaverPlaceSearchService naverPlaceSearchService;
    private final SearchCountRepository searchCountRepository;

    @Override
    public PlaceSearchResponse getPlaceSearchResponse(String keyword) {
        var searchInfos = getSearchInfos(keyword);
        var keywordTop10 = getKeywordTop10();

        return PlaceSearchResponse.builder()
                .places(searchInfos)
                .keywordTop10(keywordTop10)
                .build();
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "insertOrUpdateSearchCount", fallbackMethod = "insertOrUpdateSearchCountFallback")
    public void insertOrUpdateSearchCount(String keyword) {
        var searchCount = searchCountRepository.findByKeyword(keyword)
                .orElseGet(() -> {
                    searchCountRepository.save(SearchCount.builder()
                            .keyword(keyword)
                            .count(0L)
                            .build());
                    return searchCountRepository.findByKeyword(keyword).get();
                });
        searchCount.setCount(searchCount.getCount() + 1L);
    }

    @CircuitBreaker(name = "placeSearchInfo", fallbackMethod = "placeSearchFallback")
    private List<PlaceSearchItem> getSearchInfos(String keyword) {

        final int MAX_SEARCH_COUNT = 10;
        var kakaoSearchInfos = kakaoPlaceSearchService.getPlaceSearchInfo(keyword);
        var naverSearchInfos = naverPlaceSearchService.getPlaceSearchInfo(keyword);

        var overlapInfos = kakaoSearchInfos.stream()
                .filter(kakao -> naverSearchInfos.stream()
                        .anyMatch(naver -> naver.getTitle().equals(kakao.getTitle())))
                .toList();

        var onlyKakaoInfos = kakaoSearchInfos.stream()
                .filter(kakao -> overlapInfos.stream().noneMatch(overlap -> overlap.getTitle().equals(kakao.getTitle())))
                .toList();
        var onlyNaverInfos = naverSearchInfos.stream()
                .filter(naver -> overlapInfos.stream().noneMatch(overlap -> overlap.getTitle().equals(naver.getTitle())))
                .toList();


        var countWithoutOverlap = MAX_SEARCH_COUNT - overlapInfos.size();
        var onlyKakaoCount = countWithoutOverlap % 2 == 0 ? countWithoutOverlap / 2 : countWithoutOverlap / 2 + 1;
        var onlyNavercount = countWithoutOverlap / 2;
        if (!(onlyKakaoInfos.size() < onlyKakaoCount && onlyNaverInfos.size() < onlyNavercount)) {
            onlyKakaoCount = onlyNaverInfos.size() < onlyNavercount ? onlyKakaoCount + (onlyNavercount - onlyNaverInfos.size()) : onlyKakaoCount;
            onlyNavercount = onlyKakaoInfos.size() < onlyKakaoCount ? onlyNavercount + (onlyKakaoCount - onlyKakaoInfos.size()) : onlyNavercount;
        }

        List<PlaceSearchItem> searchInfos = new ArrayList<>();
        searchInfos.addAll(overlapInfos);
        searchInfos.addAll(onlyKakaoInfos.stream().limit(onlyKakaoCount).toList());
        searchInfos.addAll(onlyNaverInfos.stream().limit(onlyNavercount).toList());

        return searchInfos;
    }

    private List<SearchCount> getKeywordTop10() {
        return searchCountRepository.findTop10ByOrderByCountDesc();
    }

    private List<PlaceSearchItem> placeSearchFallback(String keyword, Exception e) {
        log.info("PlaceSearchServiceImpl getSearchInfos fallback: keyword -", keyword, e.getMessage());
        return Collections.emptyList();
    }

    private void insertOrUpdateSearchCountFallback(String keyword, Exception e) {
        log.info("PlaceSearchServiceImpl insertOrUpdateSearchCount fallback: keyword -", keyword, e.getMessage());
    }
}
