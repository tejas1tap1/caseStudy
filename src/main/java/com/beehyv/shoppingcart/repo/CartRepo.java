package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Cart;
import com.beehyv.shoppingcart.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findCartByUserProfile(UserProfile userProfile);
}
