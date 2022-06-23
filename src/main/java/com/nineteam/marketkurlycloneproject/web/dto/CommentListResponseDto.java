package com.nineteam.marketkurlycloneproject.web.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {

    private Long productId;
    private Long commentId;
    private String title;

    private String comment_image;
    private String comment;
    private String username;
    private String createdDate;



    @QueryProjection
    public CommentListResponseDto(Long productId, Long commentId, String title, String comment_image, String comment, String username, LocalDateTime createdDate) {
        this.productId = productId;
        this.commentId = commentId;
        this.title = title;
        this.comment_image = comment_image;
        this.comment = comment;
        this.username = username;
        this.createdDate = createdDate.toString();
    }
}
