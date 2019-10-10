package com.beehyv.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import javax.persistence.*;

@Entity
public class Cart {
	   @Id
	   @Column(name="cartId")
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private long cartId;
	   @JsonIgnoreProperties(value ="cartItems", allowSetters=true)
	   @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	   private List<CartItem> cartItems;
	   @OneToOne
	   private UserProfile userProfile;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"cartId=" + cartId +
				", userProfile=" + userProfile +
				'}';
	}
}
