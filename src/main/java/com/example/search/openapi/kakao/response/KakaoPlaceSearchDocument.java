package com.example.search.openapi.kakao.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoPlaceSearchDocument {

    private String id;

    @JsonProperty("place_name")
    private String placeName;

    private String distance;

    @JsonProperty("place_url")
    private String placeUrl;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("road_address_name")
    private String roadAddressName;

    private String phone;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("category_group_name")
    private String categoryGroupName;

    private String x;

    private String y;
}
