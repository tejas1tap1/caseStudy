package com.beehyv.shoppingcart.dto;

import com.beehyv.shoppingcart.entity.SubCategory;

import java.util.List;

public class Filters {
    private List<String> subCategories;
    private long minPrice;
    private long maxPrice;

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(long minPrice) {
        this.minPrice = minPrice;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "Filters{" +
                "subCategories=" + subCategories +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
