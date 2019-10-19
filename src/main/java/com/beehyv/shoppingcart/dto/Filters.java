package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.SubCategory;

import java.util.List;

public class Filters {
    private List<String> subCategories;
    private Double minPrice;
    private Double maxPrice;

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
