package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.OrderItemDTO;
import com.beehyv.shoppingcart.dto.OrdersDTO;
import com.beehyv.shoppingcart.entity.OrderItem;
import com.beehyv.shoppingcart.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(uses = {OrderItemMapper.class})
public interface OrdersMapper {
  OrdersMapper INSTANCE= Mappers.getMapper(OrdersMapper.class);
  @Mapping(target = "orderItemDTOS", source = "orderItems")
  OrdersDTO toOrdersDTO(Orders orders);
  Orders toOrders(OrdersDTO ordersDTO);

  List<OrdersDTO> toOrdersDTOS(List<Orders> orders);

  List<Orders> toOrders(List<OrdersDTO> ordersDTOS);
}
