package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.CartItemDTO;
import com.beehyv.shoppingcart.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartItemMapper {
    CartItemMapper INSTANCE= Mappers.getMapper(CartItemMapper.class);
    List<CartItemDTO>  toCartItemsDTO(List<CartItem> cartItems);
    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

}
