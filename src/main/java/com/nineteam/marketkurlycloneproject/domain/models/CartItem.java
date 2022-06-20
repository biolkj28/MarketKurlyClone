package com.nineteam.marketkurlycloneproject.domain.models;

import com.nineteam.marketkurlycloneproject.web.dto.CartItemRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class CartItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productsId")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "cartsId")
    private Carts carts;

    @Column
    private Integer quantity;

    @Column
    private int totalPrice;

    public CartItem(CartItemRequestDto cartIemRequestDto, User user, Products products, Carts carts) {

        this.quantity = cartIemRequestDto.getQuantity() ;
        this.totalPrice = products.getPrice() * cartIemRequestDto.getQuantity();
        this.user = user;
        this.products = products;
        this.carts = carts;
    }

    public void update (Integer quantity) {
        this.quantity = quantity;
    }

}
