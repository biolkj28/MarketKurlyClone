package com.nineteam.marketkurlycloneproject.web.dto;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.security.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long commentId;
    private Products products;
    private User user;
    private String title;
    private String comment;
    private String commentImg;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.user = comment.getUser();
        this.products = comment.getProducts();
        this.title = comment.getTitle();
        this.comment = comment.getComment();
        this.commentImg = comment.getCommentImg();
    }

}
