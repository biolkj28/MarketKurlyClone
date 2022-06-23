package com.nineteam.marketkurlycloneproject.web.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {

    private Long commentId;
    private String title;
    private String username;

    private String createdDate;

    @QueryProjection
    public CommentListResponseDto(Long commentId, String title, String username, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.title = title;
        this.username = username;
        this.createdDate = createdDate.toString();
    }
}
