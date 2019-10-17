package com.beehyv.shoppingcart.controller;


import com.beehyv.shoppingcart.dto.CartItemDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.mapper.CartItemMapper;
import com.beehyv.shoppingcart.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServices cartServices;

    @GetMapping("/cart/{userId}/get-cart")
    public List<CartItemDTO> getAllCartItems(@PathVariable("userId") long userId) {
        return CartItemMapper.INSTANCE.toCartItemsDTO(cartServices.getAllCartItems(userId));
    }
    @GetMapping("/cart/{userId}/get-cart-item/{cartItemId}")
    public CartItemDTO getCartItem(@PathVariable("userId") long userId,@PathVariable("cartItemId")long cartItemId)
    {
        return CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.getCartItem(userId,cartItemId));
    }
    @PostMapping("/cart/{userId}/add/{productId}")
    public CartItemDTO addToCart(@PathVariable("userId") long userId, @PathVariable("productId")long productId)
    {
        return CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.addToCart(userId,productId));
    }
    @GetMapping("/cart/{userId}/remove/{productId}")
    public String removeFromCart(@PathVariable("userId") long userId,@PathVariable("productId")long productId)
    {
        return cartServices.removeFromCart(userId,productId);
    }
    @PutMapping("/cart/{userId}/changeQuantity/{productId}")
    public CartItemDTO changeQuantityOfProduct(@PathVariable("userId") long userId,@PathVariable("productId")long productId,@RequestBody long quantity)
    {
        return  CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.changeQuantityOfProduct(userId, productId, quantity));
    }

}
