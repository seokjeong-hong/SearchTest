package com.example.search.openapi.kakao.service.v2;

import com.example.search.response.PlaceSearchItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KakaoPlaceSearchServiceImplTest {

    @Autowired
    KakaoPlaceSearchService kakaoPlaceSearchService;

    @Test
    void searchByKeywordTest() {
        List<PlaceSearchItem> documents = kakaoPlaceSearchService.getPlaceSearchInfo("곱창");
        System.out.println(documents);
        String title = documents.get(0).getTitle();
        assertEquals(title,"해성막창집 본점");
    }

}