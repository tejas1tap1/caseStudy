package com.beehyv.shoppingcart.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class CartItem {
    @Id
    @Column(name = "cartItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;
    @ManyToOne
    private Product product;
    @Min(1)
    @Column
    private long quantity;

    @ManyToOne
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


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

    @Override
    public String toString() {
        return "CartItem [cartItemId=" + cartItemId + ", product=" + product + ", quantity=" + quantity + "]";
    }

}
