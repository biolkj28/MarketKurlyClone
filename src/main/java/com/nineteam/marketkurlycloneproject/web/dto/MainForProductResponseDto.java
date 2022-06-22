package com.nineteam.marketkurlycloneproject.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainForProductResponseDto {
    private Long productId;
    private String image_url;
    private String title;
    private int price;

    @QueryProjection
    public MainForProductResponseDto(Long productId, String image_url, String title, int price) {
        this.productId = productId;
        this.image_url = image_url;
        this.title = title;
        this.price = price;
    }
}
