package com.beehyv.shoppingcart.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class SubCategory {
	 @Id
	   @Column(name="subCategoryId")
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long subCategoryId;
	 @Column(name="name")
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
	@Override
	public String toString() {
		return "SubCategory{" +
				"subCategoryId=" + subCategoryId +
				", name='" + name + '\'' +
				'}';
	}
}
