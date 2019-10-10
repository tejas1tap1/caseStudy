package com.beehyv.shoppingcart.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItem;
    @Column
    private String orderStatus;
	@ManyToOne
	UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderItem=" + orderItem +
                ", orderStatus='" + orderStatus + '\'' +
                ", userProfile=" + userProfile +
                '}';
    }
}
