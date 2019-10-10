package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.CartItem;

import java.util.List;

public class CartDTO {
    private long cartId;
    private List<CartItemDTO> cartItemsDTOS;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public List<CartItemDTO> getCartItemsDTOS() {
        return cartItemsDTOS;
    }

    public void setCartItemsDTOS(List<CartItemDTO> cartItemsDTOS) {
        this.cartItemsDTOS = cartItemsDTOS;
    }
}
