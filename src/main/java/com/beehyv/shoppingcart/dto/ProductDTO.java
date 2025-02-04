package com.beehyv.shoppingcart.dto;

import java.util.List;

public class ProductDTO {
    private long productId;
    private String name;
    private double price;
    private String details;
    private CategoryDTO categoryDTO;
    private List<SubCategoryDTO> subCategoryDTOS;

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

    public List<SubCategoryDTO> getSubCategoryDTOS() {
        return subCategoryDTOS;
    }

    public void setSubCategoryDTOS(List<SubCategoryDTO> subCategoryDTOS) {
        this.subCategoryDTOS = subCategoryDTOS;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", categoryDTO=" + categoryDTO +
                ", subCategoryDTOS=" + subCategoryDTOS +
                '}';
    }
}
