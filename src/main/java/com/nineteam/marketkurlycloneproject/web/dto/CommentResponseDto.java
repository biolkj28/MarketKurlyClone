package com.nineteam.marketkurlycloneproject.web.dto;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.security.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

//    private Long commentId;
    private Long productsId;
    private String loginId;
    private String title;
    private String createdAt;
    private String commentImg;
    private int view;

    public CommentResponseDto(Comment comment) {
//        this.commentId = comment.getId();
        this.productsId = comment.getProducts().getId();
        this.loginId = comment.getUser().getLoginId();
        this.title = comment.getTitle();
        this.createdAt = comment.getCreatedAt();
        this.view = comment.getView();
    }
}
