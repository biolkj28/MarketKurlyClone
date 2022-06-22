package com.nineteam.marketkurlycloneproject.web.dto;


import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDto {

    private String title;
    // 설명
    private String desc;
    //링크
    private String link;
    //이미지
    private String image;
    //가격
    private int lprice;

    //판매 단위
    private String unit;

    //중량/용량
    private String weight;

    //배송구분
    private String shipping;

    //포장 타입
    private String packing;

    private String mallName;

    @Builder
    public ProductResponseDto(
            String title,
            String desc,
            String link,
            String image,
            int lprice,
            String mallName,
            String unit,
            String weight,
            String shipping,
            String packing
    ) {
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
        this.unit = unit;
        this.mallName = mallName;
        this.weight = weight;
        this.shipping = shipping;
        this.packing = packing;

    }
}
