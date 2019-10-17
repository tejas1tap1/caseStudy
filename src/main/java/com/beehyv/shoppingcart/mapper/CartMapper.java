package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.CartDTO;
import com.beehyv.shoppingcart.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO cartToCartDTO(Cart cart);
    Cart toCart(CartDTO cartDTO);
}
