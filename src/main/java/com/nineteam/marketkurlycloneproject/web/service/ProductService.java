package com.nineteam.marketkurlycloneproject.web.service;


import com.nineteam.marketkurlycloneproject.domain.repository.ProductsQueryRepository;
import com.nineteam.marketkurlycloneproject.web.dto.MainForProductResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsQueryRepository repository;

    public List<MainForProductResponseDto> getMain(){
        return repository.getMainForProducts();
    }

    public ProductDetailResponse getProductDetail(Long productId){
        return repository.getProductDetail(productId).orElseThrow(()->new NullPointerException("없는 상품입니다."));
    }
}
