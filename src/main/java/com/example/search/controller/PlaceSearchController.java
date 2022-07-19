package com.example.search.controller;

import com.example.search.response.PlaceSearchResponse;
import com.example.search.service.PlaceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlaceSearchController {

    private final PlaceSearchService placeSearchService;

    @GetMapping("/v1/place")
    private PlaceSearchResponse search(@RequestParam(name = "q") String keyword) {
        placeSearchService.insertOrUpdateSearchCount(keyword);
        return placeSearchService.getPlaceSearchResponse(keyword);
    }
}
