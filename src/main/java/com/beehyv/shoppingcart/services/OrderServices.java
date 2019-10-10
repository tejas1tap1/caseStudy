package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import com.beehyv.shoppingcart.entity.UserProfile;
import com.beehyv.shoppingcart.repo.OrdersRepo;
import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServices {
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private UserProfileRepo userProfileRepo;
    public List<Orders> orderHistory(long userId) {
        return ordersRepo.findByUserProfile(userProfileRepo.findByUserId(userId));
    }
    public Orders createOrder(long userId, List<CartItem> cartItems) {
        UserProfile userProfile=userProfileRepo.findByUserId(userId);
        int n=cartItems.size();
        List<OrderItem> orderItems=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            OrderItem orderItem=new OrderItem();
            orderItem.setProduct(cartItems.get(i).getProduct());
            orderItem.setQuantity(cartItems.get(i).getQuantity());
            orderItems.add(orderItem);
        }
        Orders orders =new Orders();
        orders.setOrderItem(orderItems);
        return ordersRepo.save(orders);

    }
}
