package com.example.search.openapi.naver.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NaverPlaceSearchClientTest {
    private static final String CLIENT_ID = "bcohvWM678ZKag5AodVZ";
    private static final String CLIENT_SECRET = "GFto3WWpsz";
    private static final int SIZE = 5;

    @Autowired
    NaverPlaceSearchClient naverPlaceSearchClient;

    @Test
    void getResponse() {
        //정상호출
        var responese = naverPlaceSearchClient.getNaverPlaceSearchResponse(CLIENT_ID, CLIENT_SECRET, "%EA%B3%B1%EC%B0%BD", SIZE);
        System.out.println(responese);
        assertTrue(responese.getBody().getItems().size() > 0);
    }

    @Test
    void getResponseStatusCode() {
        //정상호출
        var responese = naverPlaceSearchClient.getNaverPlaceSearchResponse(CLIENT_ID, CLIENT_SECRET, "%EA%B3%B1%EC%B0%BD", SIZE);
        System.out.println(responese.getStatusCode());
        assertEquals(responese.getStatusCode(), HttpStatus.OK);
    }

}