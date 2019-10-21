package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.OrderItemDTO;
import com.beehyv.shoppingcart.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(target = "productDTO", source = "product")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);

    OrderItem toOrderItem(OrderItemDTO orderItemDTO);

    List<OrderItemDTO> toOrderItemDTOS(List<OrderItem> orderItems);

    List<OrderItem> toOrderItems(List<OrderItemDTO> orderItemDTOS);


}
