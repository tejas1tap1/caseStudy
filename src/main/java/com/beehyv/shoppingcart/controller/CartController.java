package com.beehyv.shoppingcart.controller;


import com.beehyv.shoppingcart.dto.CartItemDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.mapper.CartItemMapper;
import com.beehyv.shoppingcart.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServices cartServices;
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/get-cart")
    public  ResponseEntity getAllCartItems(@PathVariable("userId") long userId) {
        if(CartItemMapper.INSTANCE.toCartItemsDTO(cartServices.getAllCartItems(userId))==null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("empty");
        return ResponseEntity.ok(CartItemMapper.INSTANCE.toCartItemsDTO(cartServices.getAllCartItems(userId)));
    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/get-cart-item/{cartItemId}")
    public CartItemDTO getCartItem(@PathVariable("userId") long userId,@PathVariable("cartItemId")long cartItemId)
    {
        return CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.getCartItem(userId,cartItemId));
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/cart/{userId}/add/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable("userId") long userId, @PathVariable("productId")long productId)
    {
        return ResponseEntity.ok(CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.addToCart(userId,productId)));
    }
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/remove/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable("userId") long userId,@PathVariable("productId")long productId)
    {
        return ResponseEntity.ok(cartServices.removeFromCart(userId,productId));
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/cart/{userId}/changeQuantity/{productId}")
    public CartItemDTO changeQuantityOfProduct(@PathVariable("userId") long userId,@PathVariable("productId")long productId,@RequestBody long quantity)
    {
        return  CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.changeQuantityOfProduct(userId, productId, quantity));
    }

}
