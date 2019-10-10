package com.beehyv.shoppingcart.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SubCategory {
	 @Id
	   @Column(name="subCategoryId")
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long subCategoryId;
	 @Column(name="name")
	   private String name;
	 @ManyToMany(mappedBy = "subcategories")
	 private List<Product> products;
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


	@Override
	public String toString() {
		return "SubCategory{" +
				"subCategoryId=" + subCategoryId +
				", name='" + name + '\'' +
				'}';
	}
}
