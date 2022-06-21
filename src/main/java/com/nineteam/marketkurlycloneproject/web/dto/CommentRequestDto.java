package com.nineteam.marketkurlycloneproject.web.dto;

import com.nineteam.marketkurlycloneproject.domain.model.Products;
import com.nineteam.marketkurlycloneproject.security.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequestDto {

    private Products products;
    private User user;
    private Long commentId;
    private String title;
    private String comment;
    private String commentImg;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String FileName;

}
