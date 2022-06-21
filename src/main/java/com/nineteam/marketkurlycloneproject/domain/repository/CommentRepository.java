package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProductsId(Long productsId);
}