package com.nineteam.marketkurlycloneproject.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String img;

    @Column
    private String unit;

    @Column
    private String weight;

    @Column
    private String shopping;

    @Column
    private String packing;

    @Column
    private Integer price;

}
