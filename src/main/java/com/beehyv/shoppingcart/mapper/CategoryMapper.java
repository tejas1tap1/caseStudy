package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.CategoryDTO;
import com.beehyv.shoppingcart.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
}
