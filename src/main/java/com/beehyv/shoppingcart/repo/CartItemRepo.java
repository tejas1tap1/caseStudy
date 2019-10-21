package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Cart;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findByCartItemIdAndCart(long cartItemId, Cart cart);

    CartItem findByProductAndCart(Product product, Cart cart);
}
