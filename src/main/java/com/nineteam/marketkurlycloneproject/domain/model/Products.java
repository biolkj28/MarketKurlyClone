package com.nineteam.marketkurlycloneproject.domain.model;


import com.nineteam.marketkurlycloneproject.web.dto.ProductResponseDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Products extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String title;

    // 설명
    @Column
    private String desc;

    //이미지
    @Column
    private String image;

    //가격
    @Column(nullable = false)
    private int lprice;

    //판매 단위
    @Column
    private String unit;

    //중량/용량
    @Column
    private String weight;

    //배송구분
    @Column
    private String shipping;

    //포장 타입
    @Column
    private String packing;

    //원산지
    @Column
    private String origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category")
    @ToString.Exclude
    private Category category;

    @Builder
    public Products(
            Long id,
            String title,
            String desc,
            String image,
            int lprice,
            String unit,
            String weight,
            String shipping,
            String packing,
            String origin,
            Category category
    ) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.lprice = lprice;
        this.unit = unit;
        this.weight = weight;
        this.shipping = shipping;
        this.packing = packing;
        this.origin = origin;
        this.category = category;
    }
}
