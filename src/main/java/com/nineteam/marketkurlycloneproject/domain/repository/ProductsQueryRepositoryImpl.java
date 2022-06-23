package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.domain.model.QProducts;
import com.nineteam.marketkurlycloneproject.web.dto.MainForProductResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.ProductDetailResponse;
import com.nineteam.marketkurlycloneproject.web.dto.QMainForProductResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.QProductDetailResponse;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.nineteam.marketkurlycloneproject.domain.model.QProducts.products;


@Repository
@RequiredArgsConstructor
public class ProductsQueryRepositoryImpl implements ProductsQueryRepository {

    private final JPAQueryFactory query;

    // 상품 데이터 조회
    @Override
    public List<MainForProductResponseDto> getMainForProducts() {
        return query
                .select(new QMainForProductResponseDto(
                        products.id,
                        products.image,
                        products.title,
                        products.lprice
                ))
                .from(products)
                .orderBy(products.title.asc())
                .limit(20)
                .fetch();
    }

    @Override
    public Optional<ProductDetailResponse> getProductDetail(Long productId) {
        return Optional.ofNullable(query.select(
                        new QProductDetailResponse(
                                products.id,
                                products.title,
                                products.image,
                                products.desc,
                                products.lprice
                        )

                ).from(products)
                .where(products.id.eq(productId))
                .fetchOne()
        );
    }

}
