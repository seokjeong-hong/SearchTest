package com.example.search.openapi.kakao.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class KakaoPlaceSearchClientTest {
    private static final String REST_API_KEY = "KakaoAK 58c1c91b36d83f82ea5b304d91efa1a6";
    private static final int SIZE = 5;

    @Autowired
    KakaoPlaceSearchClient kakaoPlaceSearchClient;

    @Test
    void getResponse() {
        var response = kakaoPlaceSearchClient.getKakaoPlaceSearchResponse(REST_API_KEY, "곱창", SIZE);
        assertTrue(response.getBody().getDocuments().size() > 0);
    }

    @Test
    void getResponseStatusCode() {
        //정상호출
        var responese = kakaoPlaceSearchClient.getKakaoPlaceSearchResponse(REST_API_KEY, "곱창", SIZE);
        System.out.println(responese.getStatusCode());
        assertEquals(responese.getStatusCode(), HttpStatus.OK);
    }


}