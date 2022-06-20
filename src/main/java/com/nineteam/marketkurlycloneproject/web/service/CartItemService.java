package com.nineteam.marketkurlycloneproject.web.service;


import com.nineteam.marketkurlycloneproject.domain.models.CartItem;
import com.nineteam.marketkurlycloneproject.domain.models.Carts;
import com.nineteam.marketkurlycloneproject.domain.models.Products;
import com.nineteam.marketkurlycloneproject.domain.repository.CartItemRepository;
import com.nineteam.marketkurlycloneproject.domain.repository.CartsRepository;
import com.nineteam.marketkurlycloneproject.security.UserDetailsImpl;
import com.nineteam.marketkurlycloneproject.web.dto.CartItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartItemService {

    private final UserRepository userRepository;
    private final CartsRepository cartsRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItemRequestDto> getCarts(Long userId) {

        Carts carts = cartsRepository.findByUserId(userId).orElseThrow(
                ()-> new NullPointerException("장바구니가 존재하지 않습니다."));

        return getCartItemRequestDtoList(userId, carts);
    }

    @Transactional
    public List<CartItemRequestDto> createCartItem(CartItemRequestDto cartItemRequestDto) {

        Long userId = cartItemRequestDto.getUserId();
        Long productsId = cartItemRequestDto.getProductsId();
        Integer quantity = cartItemRequestDto.getQuantity();


        User user = userRepository.findById(userId).orElseThrow(
                ()-> new NullPointerException("해당 유저 아이디가 없습나다."));

        Products products = productRepository.findByProductsId(productsId);

        Carts carts = cartsRepository.findByUserId(userId).orElseThrow(
                ()-> new NullPointerException("장바구니가 존재하지 않습니다."));

        //장바구니 제품 중복확인
        if(cartItemRepository.findByUserIdAndProductsId(userId, productsId).isPresent()){
            CartItem cartItem = cartItemRepository.findByUserIdAndProductsId(userId, productsId).orElseThrow(
                    () -> new NullPointerException("장바구니에 상품이 없습니다."));
             quantity = quantity + cartItem.getQuantity();

             cartItem.update(quantity);

             return getCartItemRequestDtoList(userId, carts);
        }else {

            CartItem cartItem = new CartItem(cartItemRequestDto, user, products, carts);

            cartItemRepository.save(cartItem);

            return getCartItemRequestDtoList(userId, carts);
        }
    }

    private List<CartItemRequestDto> getCartItemRequestDtoList(Long userId, Carts carts) {
        Long cartId = carts.getId();
        List<CartItem>cartItemList = cartItemRepository.findAllByCartsId(cartId);

        List<CartItemRequestDto> cartItemRequestDtoList = new ArrayList<>();
        for(CartItem cartItem : cartItemList){
            Long productId = cartItem.getProducts().getId();
            Products products = productRepository.findByProductsId(productId);

            String name = products.getName();
            Integer price = products.getPrice();
            String img = products.getImg();
            Integer quantity = cartItem.getQuantity();
            Long cartItemId = cartItem.getId();

            CartItemRequestDto cartItemRequestDto = new CartItemRequestDto();
            cartItemRequestDto.setUserId(userId);
            cartItemRequestDto.setProductsId(productId);
            cartItemRequestDto.setName(name);
            cartItemRequestDto.setPrice(price);
            cartItemRequestDto.setImg(img);
            cartItemRequestDto.setQuantity(quantity);
            cartItemRequestDto.setCartItemId(cartItemId);

            cartItemRequestDtoList.add(cartItemRequestDto);
        }
        return cartItemRequestDtoList;
    }

    @Transactional
    public void deleteCartItem(Long productsId, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        CartItem cartItem = cartItemRepository.findByUserIdAndProductsId(userId, productsId).orElseThrow(
                () -> new NullPointerException("장바구니에 상품이 존재하지 않습니다."));

        Long cartItemId = cartItem.getId();

        cartItemRepository.deleteById(cartItemId);
    }


    public void updateCartItem(CartItemRequestDto cartItemRequestDto, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Long productsId = cartItemRequestDto.getProductsId();

        Integer quantity = cartItemRequestDto.getQuantity();

        CartItem cartItem = cartItemRepository.findByUserIdAndProductsId(userId, productsId).orElseThrow(
                ()-> new NullPointerException("장바구니에 상품이 존재하지 않습니다."));

        cartItem.update(quantity);
    }
}