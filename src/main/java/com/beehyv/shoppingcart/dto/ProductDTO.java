package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.Category;
import com.beehyv.shoppingcart.entity.SubCategory;

import java.util.List;

public class ProductDTO {
    private long productId;
    private String name;
    private long price;
    private String details;
    private CategoryDTO categoryDTO;
    private SubCategoryDTO subCategoryDTO;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public SubCategoryDTO getSubCategoryDTO() {
        return subCategoryDTO;
    }

    public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
        this.subCategoryDTO = subCategoryDTO;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
