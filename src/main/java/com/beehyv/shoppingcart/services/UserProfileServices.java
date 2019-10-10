package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.UserProfile;
import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserProfileServices {
    @Autowired
    private UserProfileRepo userProfileRepo;

    public UserProfile getProfile(long userId) {

        return userProfileRepo.findByUserId(userId);
    }

    public ResponseEntity<?> updateProfile(UserProfile userProfile) {
        userProfileRepo.save(userProfile);
        return ResponseEntity.ok("Success");

    }
}
