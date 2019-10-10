package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.OrderItemDTO;
import com.beehyv.shoppingcart.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper INSTANCE= Mappers.getMapper(OrderItemMapper.class);

    OrderItemDTO ToOrderDTO(OrderItem orderItem);

}
