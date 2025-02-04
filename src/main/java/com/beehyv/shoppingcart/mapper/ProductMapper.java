package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.ProductDTO;
import com.beehyv.shoppingcart.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CategoryMapper.class, SubCategoryMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "subCategoryDTOS", source = "subCategories")
    @Mapping(target = "categoryDTO", source = "category")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "subCategories", source = "subCategoryDTOS")
    @Mapping(target = "category", source = "categoryDTO")
    Product toProduct(ProductDTO productDTO);

    List<ProductDTO> toProductDTOS(List<Product> products);

}
