package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.dto.OrdersDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import com.beehyv.shoppingcart.mapper.OrdersMapper;
import com.beehyv.shoppingcart.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServices orderServices;

    @GetMapping("/order/{userId}/getOrders")
    public List<Orders> orderHistory(@PathVariable("userId") long userId) {
        return orderServices.orderHistory(userId);
    }

    @GetMapping("/order/{userId}/createOrder")
    public OrdersDTO createOrder(@PathVariable("userId") long userId, List<CartItem> cartItems) {
        return OrdersMapper.INSTANCE.toOrdersDTO(orderServices.createOrder(userId, cartItems));
    }
}
