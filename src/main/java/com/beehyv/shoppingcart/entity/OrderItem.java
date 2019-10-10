package com.beehyv.shoppingcart.entity;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @Column(name = "orderItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long OrderItemId;
    @OneToOne
    private Product product;
    @Column
    private long quantity;
    @ManyToOne
    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }


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


    @Override
    public String toString() {
        return "OrderItem{" +
                "OrderItemId=" + OrderItemId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
