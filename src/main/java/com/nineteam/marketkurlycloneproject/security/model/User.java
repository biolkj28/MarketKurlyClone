package com.nineteam.marketkurlycloneproject.security.model;

import lombok.*;
import javax.persistence.*;

@Entity // DB 테이블 과 1:1 매핑 되는 객체
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 16, unique = true)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 8)
    private String nickName;

//    @Column(nullable = false)
//    private String address;

    @Column(nullable = false)
    private String role;

}
