package com.example.search.openapi.naver.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverPlaceSearchItem {
    String title;

    String link;

    String category;

    String description;

    String telephone;

    String address;

    @JsonProperty("road_address_name")
    String roadAddress;

    int mapx;

    int mapy;
}
