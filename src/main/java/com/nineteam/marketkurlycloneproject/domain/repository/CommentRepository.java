package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
