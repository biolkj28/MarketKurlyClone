package com.nineteam.marketkurlycloneproject.web.controller;


import com.nineteam.marketkurlycloneproject.domain.models.CartItem;
import com.nineteam.marketkurlycloneproject.security.UserDetailsImpl;
import com.nineteam.marketkurlycloneproject.web.dto.CartItemRequestDto;
import com.nineteam.marketkurlycloneproject.web.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CartsController {

    private final CartItemService cartItemService;

    //장바구니 조회
    @GetMapping("/carts")
    public List<CartItemRequestDto> getCarts(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        List<CartItemRequestDto> cartItemRequestDtoList = cartItemService.getCarts(userId);
        return cartItemRequestDtoList;
    }

    // 장바구니 생성
    @PostMapping("/carts/{productsId}")
    public List<CartItemRequestDto> creatCart(@RequestBody CartItemRequestDto cartItemRequestDto,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return null;
        } else {
            Long userId = userDetails.getUser().getId();
            cartItemRequestDto.setUserId(userId);

            List<CartItemRequestDto> cartItemRequestDtoList = cartItemService.createCartItem(cartItemRequestDto);

            return cartItemRequestDtoList;

        }
    }

    @DeleteMapping("/carts/{productsId}")
    public void deleteCartItem(@PathVariable Long productsId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        cartItemService.deleteCartItem(productsId, userDetails);
    }

    @PutMapping("/carts/{productsId}")
    public Long updateCartItem(@RequestBody CartItemRequestDto cartItemRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        cartItemService.updateCartItem(cartItemRequestDto, userDetails);
        return cartItemRequestDto.getCartItemId();
    }
}
