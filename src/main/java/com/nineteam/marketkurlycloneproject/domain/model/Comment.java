package com.nineteam.marketkurlycloneproject.domain.model;

import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @Column
    private String commentImg;

    @Column
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "porductId")
    private Products products;

    public Comment(CommentRequestDto commentRequestDto, Products products, User user) {

        this.title = commentRequestDto.getTitle();
        this.comment = commentRequestDto.getComment();
        this.commentImg = commentRequestDto.getCommentImg();
        this.fileName = commentRequestDto.getFileName();
        this.user = user;
        this.products = products;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.title = commentRequestDto.getTitle();
        this.comment = commentRequestDto.getComment();
        this.commentImg = commentRequestDto.getCommentImg();
    }



}
