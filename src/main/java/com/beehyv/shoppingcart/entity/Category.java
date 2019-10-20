package com.beehyv.shoppingcart.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {
    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    @NotNull(message = "Name cannot be null")
    @Column(name="name")
    private String name;
    @OneToMany(cascade=CascadeType.ALL)
    private List<SubCategory> subCategories;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

}
