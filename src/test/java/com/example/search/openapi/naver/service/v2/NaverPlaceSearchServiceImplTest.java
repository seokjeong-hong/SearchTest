package com.example.search.openapi.naver.service.v2;

import com.example.search.response.PlaceSearchItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NaverPlaceSearchServiceImplTest {

    @Autowired
    NaverPlaceSearchService naverPlaceSearchService;

    @Test
    void searchByKeywordTest() {
        List<PlaceSearchItem> items = naverPlaceSearchService.getPlaceSearchInfo("곱창");
        String title = items.get(0).getTitle();
        System.out.println(items);
        assertEquals(title, "곱 마포점");
    }

}