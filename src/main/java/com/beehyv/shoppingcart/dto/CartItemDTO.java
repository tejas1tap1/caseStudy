package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.Product;

public class CartItemDTO {
    private long cartItemId;
    private Product product;
    private long quantity;

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
