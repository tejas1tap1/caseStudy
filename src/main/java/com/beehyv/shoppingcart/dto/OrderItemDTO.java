package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.Product;

public class OrderItemDTO {
    private long OrderItemId;
    private ProductDTO productDTO;
    private long quantity;

    public long getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        OrderItemId = orderItemId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "OrderItemId=" + OrderItemId +
                ", productDTO=" + productDTO +
                ", quantity=" + quantity +
                '}';
    }
}
