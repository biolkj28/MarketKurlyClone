package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.web.dto.MainForProductResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.ProductDetailResponse;

import java.util.List;
import java.util.Optional;


public interface ProductsQueryRepository {
    List<MainForProductResponseDto> getMainForProducts();

    Optional<ProductDetailResponse> getProductDetail(Long productId);
}
