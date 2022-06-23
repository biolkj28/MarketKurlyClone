package com.nineteam.marketkurlycloneproject.domain.model;

import com.nineteam.marketkurlycloneproject.security.model.User;
import com.nineteam.marketkurlycloneproject.web.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @Column
    private String comment_image;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "porductId")
    private Products products;

    public Comment(String title, String comment, String comment_image, User user, Products products){
        this.title = title;
        this.comment = comment;
        this.comment_image = comment_image;
        this.user = user;
        this.products = products;
    }

    public void update(String title, String comment, String comment_image) {
        this.title = title;
        this.comment = comment;
        this.comment_image = comment_image;
    }

}
