package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.models.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartsRepository extends JpaRepository<Carts, Long> {
    Optional<Carts> findByUserId(Long userId);
}
