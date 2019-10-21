package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Orders;
import com.beehyv.shoppingcart.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByUserProfile(UserProfile userProfile);

}
