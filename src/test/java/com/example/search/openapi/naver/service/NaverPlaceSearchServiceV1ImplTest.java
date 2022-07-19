package com.example.search.openapi.naver.service;

import com.example.search.openapi.naver.service.v1.NaverPlaceSearchServiceV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NaverPlaceSearchServiceV1ImplTest {

    @Autowired
    NaverPlaceSearchServiceV1 naverPlaceSearchService;

    @Test
    void searchByKeywordTest() {
        var items = naverPlaceSearchService.getPlaceSearchInfo("곱창");
        var title = items.get(0).getTitle();
        System.out.println(items);
        assertEquals(title, "곱 마포점");
    }

    @Test
    void fallbackTest() {
        var items = naverPlaceSearchService.getPlaceSearchInfo("");
        assertTrue(items.isEmpty());
    }

}