package com.nineteam.marketkurlycloneproject.web.dto;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.security.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long commentId;
    private String title;
    private String loginId;
    private String comment;
    private String createdAt;
    private String commentImg;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.title = comment.getTitle();
        this.loginId = comment.getUser().getLoginId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.commentImg = comment.getCommentImg();
    }

}
