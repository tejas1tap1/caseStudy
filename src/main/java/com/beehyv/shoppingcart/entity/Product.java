package com.beehyv.shoppingcart.entity;



import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Product {
	   @Id
	   @Column(name="productId")
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long productId;
	   @NotNull(message = "Name cannot be null")
	   @Column(name="name")
	   private String name;
	   @Column
	   private long price;
	   @Column
	   private String details;
	   @ManyToOne(cascade=CascadeType.ALL)
	   private Category category;
	   @ManyToOne(cascade=CascadeType.ALL)
	   private SubCategory subCategory;

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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", name='" + name + '\'' +
				", price=" + price +
				", details='" + details + '\'' +
				", category=" + category +
				", subCategory=" + subCategory +
				'}';
	}
}
