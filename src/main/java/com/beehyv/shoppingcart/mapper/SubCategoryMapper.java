package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.SubCategoryDTO;
import com.beehyv.shoppingcart.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubCategoryMapper {
    SubCategoryMapper INSTANCE= Mappers.getMapper(SubCategoryMapper.class);
    SubCategoryDTO toSubCategoryDTO(SubCategory subCategory);
    SubCategory toSubCategory(SubCategoryDTO subCategoryDTO);
    List<SubCategoryDTO> toSubCategoryDTOS (List<SubCategory> subCategories);
    List<SubCategory> toSubCategories (List<SubCategoryDTO> subCategoryDTOS);
}
