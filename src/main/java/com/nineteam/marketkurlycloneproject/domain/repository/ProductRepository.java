package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long>{
    boolean existsByTitle(String name);
}
