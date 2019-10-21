package com.beehyv.shoppingcart.dto;

public class SubCategoryDTO {
    private long subCategoryId;
    private String name;

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
