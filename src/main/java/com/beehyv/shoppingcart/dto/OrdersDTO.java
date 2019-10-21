package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.Address;

import java.util.List;

public class OrdersDTO {
    private long orderId;
    private List<OrderItemDTO> orderItemDTOS;
    private String orderStatus;
    private Address address;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemDTO> getOrderItemDTOS() {
        return orderItemDTOS;
    }

    public void setOrderItemDTOS(List<OrderItemDTO> orderItemDTOS) {
        this.orderItemDTOS = orderItemDTOS;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId=" + orderId +
                ", orderItemDTOS=" + orderItemDTOS +
                ", orderStatus='" + orderStatus + '\'' +
                ", address=" + address +
                '}';
    }
}
