package com.example.search.openapi.naver.client;

import com.example.search.openapi.naver.response.NaverPlaceSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver", url = "https://openapi.naver.com", decode404 = true)
public interface NaverPlaceSearchClient {
    @GetMapping("/v1/search/local.json")
    ResponseEntity<NaverPlaceSearchResponse> getNaverPlaceSearchResponse(@RequestHeader("X-Naver-Client-Id") String clientId, @RequestHeader("X-Naver-Client-Secret") String clientSecret,
                                                                         @RequestParam String query, @RequestParam("display") int size);
}
