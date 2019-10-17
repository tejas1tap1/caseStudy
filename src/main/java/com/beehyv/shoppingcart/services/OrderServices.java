package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.*;
import com.beehyv.shoppingcart.repo.AddressRepo;
import com.beehyv.shoppingcart.repo.CartRepo;
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
    @Autowired
    private CartServices cartServices;
    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private CartRepo cartRepo;
    public List<Orders> orderHistory(long userId) {
        System.out.println(ordersRepo.findByUserProfile(userProfileRepo.findByUserId(userId)));
        return ordersRepo.findByUserProfile(userProfileRepo.findByUserId(userId));
    }
    public Orders createOrder(long userId,long addressId) {
        UserProfile userProfile=userProfileRepo.findByUserId(userId);
        Address address=addressRepo.findByAddressId(addressId);
        List<CartItem> cartItems=cartServices.getAllCartItems(userId);
        Orders orders =new Orders();
        orders.setAddress(address);
        orders.setUserProfile(userProfile);
        orders.setOrderStatus("Your Order is on the way");
        orders=ordersRepo.save(orders);
        int n=cartItems.size();
        List<OrderItem> orderItems=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            OrderItem orderItem=new OrderItem();
            orderItem.setProduct(cartItems.get(i).getProduct());
            orderItem.setQuantity(cartItems.get(i).getQuantity());
            orderItem.setOrders(orders);
            orderItems.add(orderItem);
        }
        cartRepo.delete(cartRepo.findCartByUserProfile(userProfile));
        orders.setOrderItems(orderItems);
        return ordersRepo.save(orders);
    }
}
