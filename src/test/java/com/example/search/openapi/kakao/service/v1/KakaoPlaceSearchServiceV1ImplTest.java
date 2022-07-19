package com.example.search.openapi.kakao.service.v1;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class KakaoPlaceSearchServiceV1ImplTest {

    @Autowired
    KakaoPlaceSearchServiceV1 kakaoPlaceSearchServiceV1;

    @Test
    void searchByKeywordTest() {
        List<KakaoPlaceSearchDocument> documents = kakaoPlaceSearchServiceV1.getPlaceSearchInfo("곱창");
        String id = documents.get(0).getId();
        System.out.println(documents);
        assertEquals(id,"27503208");
    }

    @Test
    void fallbackTest() {
        List<KakaoPlaceSearchDocument> documents = kakaoPlaceSearchServiceV1.getPlaceSearchInfo("");
        assertTrue(documents.isEmpty());
    }

}