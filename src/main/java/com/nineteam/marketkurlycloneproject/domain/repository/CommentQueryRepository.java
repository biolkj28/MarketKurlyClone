package com.nineteam.marketkurlycloneproject.domain.repository;

import com.nineteam.marketkurlycloneproject.domain.model.QProducts;
import com.nineteam.marketkurlycloneproject.security.model.QUser;
import com.nineteam.marketkurlycloneproject.web.dto.CommentListResponseDto;
import com.nineteam.marketkurlycloneproject.web.dto.QCommentListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nineteam.marketkurlycloneproject.domain.model.QComment.comment1;
import static com.nineteam.marketkurlycloneproject.domain.model.QProducts.products;
import static com.nineteam.marketkurlycloneproject.security.model.QUser.*;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory query;

    public List<CommentListResponseDto> getComments(Long productId) {
        return query
                .select(new QCommentListResponseDto(
                        comment1.products.id,
                        comment1.id,
                        comment1.title,
                        comment1.comment_image,
                        comment1.comment,
                        comment1.user.nickName,
                        comment1.createdAt
                ))
                .from(comment1)
                .innerJoin(comment1.user,user)
                .innerJoin(comment1.products,products)
                .where(comment1.products.id.eq(productId))
                .orderBy(comment1.createdAt.desc())
                .fetch();
    }

}
