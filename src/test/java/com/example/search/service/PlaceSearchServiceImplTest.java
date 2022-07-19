package com.example.search.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlaceSearchServiceImplTest {

    @Autowired
    PlaceSearchService placeSearchService;

    @Test
    void searchByKeywordTest() {
        var placeSearchResponse = placeSearchService.getPlaceSearchResponse("곱창");
        System.out.println(placeSearchResponse);
        assertEquals(placeSearchResponse.getPlaces().get(0).getTitle(), "해성막창집 본점");
    }

}