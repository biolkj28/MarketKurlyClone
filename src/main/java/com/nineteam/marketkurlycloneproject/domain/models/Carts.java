package com.nineteam.marketkurlycloneproject.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Carts extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Carts (User user) {
        this.user = user;
    }
}
