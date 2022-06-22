package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProducts(Products products);

}