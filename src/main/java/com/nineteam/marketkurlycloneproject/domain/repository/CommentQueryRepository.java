package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.web.dto.CommentDetailResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.CommentListResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.QCommentDetailResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.QCommentListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.nineteam.marketkurlycloneproject.domain.model.QComment.comment1;
import static com.nineteam.marketkurlycloneproject.security.model.QUser.*;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory query;

    public List<CommentListResponseDto> getComments(Long productId) {
        return query
                .select(new QCommentListResponseDto(comment1.id, comment1.title, comment1.user.nickName, comment1.createdAt))
                .from(comment1)
                .where(comment1.products.id.eq(productId))
                .innerJoin(user)
                .fetchJoin()
                .fetch();
    }

    public Optional<CommentDetailResponseDto> getCommentDetail(Long commentId) {
        return Optional.ofNullable(query
                .select(new QCommentDetailResponseDto(comment1.id, comment1.comment_image, comment1.comment))
                .from(comment1)
                .where(comment1.id.eq(commentId))
                .fetchOne()
        );
    }
}
