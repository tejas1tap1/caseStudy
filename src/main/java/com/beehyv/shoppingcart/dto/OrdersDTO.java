package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.OrderItem;

import java.util.List;

public class OrdersDTO {
    private long orderId;
    private List<OrderItemDTO> orderItemDTOS;
    private String orderStatus;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemDTO> getOrderItem() {
        return orderItemDTOS;
    }

    public void setOrderItem(List<OrderItemDTO> orderItemDTOS) {
        this.orderItemDTOS = orderItemDTOS;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
