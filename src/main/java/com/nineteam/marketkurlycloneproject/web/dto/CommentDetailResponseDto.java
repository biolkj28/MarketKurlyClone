package com.nineteam.marketkurlycloneproject.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CommentDetailResponseDto {

    private Long commentID;
    private String comment_image;
    private String comment;

    @QueryProjection
    public CommentDetailResponseDto(Long commentID, String comment_image, String comment) {
        this.commentID = commentID;
        this.comment_image = comment_image;
        this.comment = comment;
    }
}
