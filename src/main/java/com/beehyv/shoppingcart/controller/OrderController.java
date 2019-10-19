package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.dto.OrdersDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import com.beehyv.shoppingcart.mapper.OrdersMapper;
import com.beehyv.shoppingcart.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/order/{userId}/getOrders")
    public List<OrdersDTO> orderHistory(@PathVariable("userId") long userId) {
        System.out.println(OrdersMapper.INSTANCE.toOrdersDTOS(orderServices.orderHistory(userId)));
        return OrdersMapper.INSTANCE.toOrdersDTOS(orderServices.orderHistory(userId));
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/order/{userId}/createOrder")
    public OrdersDTO createOrder(@PathVariable("userId") long userId,@RequestBody long addressId) {
        return OrdersMapper.INSTANCE.toOrdersDTO(orderServices.createOrder(userId,addressId));
    }
}
