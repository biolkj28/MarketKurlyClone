package com.nineteam.marketkurlycloneproject.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetailResponse {
    private Long id;
    private String title;
    private String image_url;
    private String desc;
    private int price;

    @QueryProjection
    public ProductDetailResponse(Long id, String title, String image_url, String desc, int price) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.desc = desc;
        this.price = price;
    }
}
