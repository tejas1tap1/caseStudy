package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.Product;

public class OrderItemDTO {
    private long OrderItemId;
    private Product product;
    private long quantity;

    public long getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        OrderItemId = orderItemId;
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
