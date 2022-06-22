package com.nineteam.marketkurlycloneproject.web.dto;

import com.nineteam.marketkurlycloneproject.domain.model.Comment;
import com.nineteam.marketkurlycloneproject.domain.model.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDetailsDto {

    private Products products;
    private String comments;
    private String commentImg;


    public CommentDetailsDto(Products products, String comments, String commentImg) {
        this.products = products;
        this.comments = comments;
        this.commentImg = commentImg;
    }

}
