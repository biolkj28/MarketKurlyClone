//package com.nineteam.marketkurlycloneproject.domain.models;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@Getter
//@Entity
//public class CartsOrder extends Timestamped {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cartsId")
//    private Carts carts;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private User user;
//
//    public CartsOrder(Carts carts, User user) {
//        this.carts = carts;
//        this.user = user;
//    }
//}
