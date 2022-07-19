package com.example.search.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PlaceSearchItem {
    String title;

    public static PlaceSearchItem of(String title) {
        return PlaceSearchItem.builder()
                .title(title)
                .build();
    }

}

