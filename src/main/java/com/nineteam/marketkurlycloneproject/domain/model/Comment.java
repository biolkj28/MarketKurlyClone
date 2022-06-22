package com.nineteam.marketkurlycloneproject.domain.model;

import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends TimeStamped {

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

    @Column(columnDefinition = "integer default 0")
    private int view;

    @Column
    @CreatedDate
    private String createdAt;

    @Column
    @LastModifiedDate
    private String modifiedAt;

//    @Column
//    private int view;

    public Comment(CommentRequestDto commentRequestDto, Products products, User user) {

        this.title = commentRequestDto.getTitle();
        this.comment = commentRequestDto.getComment();
        this.commentImg = commentRequestDto.getCommentImg();
        this.fileName = commentRequestDto.getFileName();
        this.createdAt = commentRequestDto.getCreatedAt();
        this.user = user;
        this.products = products;
    }

    public void update(CommentRequestDto commentRequestDto){
        this.title = commentRequestDto.getTitle();
        this.comment = commentRequestDto.getComment();
        this.commentImg = commentRequestDto.getCommentImg();
        this.modifiedAt = commentRequestDto.getModifiedAt();
    }

//    public void viewCount() {
//        this.view++;
//    }



}
