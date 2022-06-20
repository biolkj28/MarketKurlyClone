package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

    List<CartItem> findAllByCartsId(Long cartsId);
    Optional<CartItem> findByUserIdAndProductsId(Long userId, Long productsId);
}
