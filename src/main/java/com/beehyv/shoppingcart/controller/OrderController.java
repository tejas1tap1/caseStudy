package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.dto.OrdersDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import com.beehyv.shoppingcart.mapper.OrdersMapper;
import com.beehyv.shoppingcart.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @Autowired
    SecurityController securityController;
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/order/{userId}/getOrders")
    public ResponseEntity orderHistory(@PathVariable("userId") long userId) {
        if(userId==securityController.currentUserId()) {

            return ResponseEntity.ok(OrdersMapper.INSTANCE.toOrdersDTOS(orderServices.orderHistory(userId)));
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");
    }
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/order/{userId}/createOrder")
    public ResponseEntity createOrder(@PathVariable("userId") long userId,@RequestBody long addressId) {
        if(userId==securityController.currentUserId()) {
               if (OrdersMapper.INSTANCE.toOrdersDTO(orderServices.createOrder(userId,addressId))==null)
                   return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Failure");
            return ResponseEntity.ok(OrdersMapper.INSTANCE.toOrdersDTO(orderServices.createOrder(userId,addressId)));
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");
    }
}
