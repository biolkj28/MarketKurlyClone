package com.nineteam.marketkurlycloneproject.domain.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TOP_CATEGORY")
    private Category topCategory;

    @OneToMany(mappedBy = "topCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory;

    @Builder
    public Category(String name, Category topCategory, List<Category> subCategory) {
        this.name = name;
        this.topCategory = topCategory;
        this.subCategory = subCategory;
    }

    public void subUpdate(List<Category>list){
        this.subCategory = list;
    }
}
