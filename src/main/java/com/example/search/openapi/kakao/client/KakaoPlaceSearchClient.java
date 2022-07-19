package com.example.search.openapi.kakao.client;

import com.example.search.openapi.kakao.response.KakaoPlaceSearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "dapi.kakao.com", decode404 = true)
public interface KakaoPlaceSearchClient {

    @GetMapping("/v2/local/search/keyword.json")
    ResponseEntity<KakaoPlaceSearchResponse> getKakaoPlaceSearchResponse(@RequestHeader("Authorization") String apiKey, @RequestParam String query, @RequestParam int size);
}
