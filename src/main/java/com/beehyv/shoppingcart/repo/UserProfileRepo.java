package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

    UserProfile findByUserId(Long userId);

    UserProfile findByEmail(String email);
}
