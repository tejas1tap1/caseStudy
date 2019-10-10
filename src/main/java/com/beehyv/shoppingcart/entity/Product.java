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
	   private String details;
	   @ManyToOne(cascade=CascadeType.ALL)
	   private Category category;

	   @ManyToMany(cascade=CascadeType.ALL)
	   private List <SubCategory> subcategories;
	public long getProductId() {
		return productId;
	}
	public void setProductId(long porductId) {
		this.productId = porductId;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<SubCategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", details=" + details + ", category=" + category
				+ ", subcategories=" + subcategories + "]";
	}
	
	
	   
}
