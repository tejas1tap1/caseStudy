package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.OrderItemDTO;
import com.beehyv.shoppingcart.dto.OrdersDTO;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface OrdersMapper {
  OrdersMapper INSTANCE= Mappers.getMapper(OrdersMapper.class);
  List<OrdersDTO> toOrdersDTOS(List<Orders> orders);
  OrdersDTO toOrdersDTO(Orders orders);
  List<OrderItemDTO> toOrdersItemsDTOS(List<OrderItem> orderItems);
}
