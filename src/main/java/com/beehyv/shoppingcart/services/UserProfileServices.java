package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.controller.SecurityController;
import com.beehyv.shoppingcart.entity.UserProfile;
import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserProfileServices {
    @Autowired
    private UserProfileRepo userProfileRepo;
    @Autowired
    SecurityController securityController;

    public UserProfile getProfile(long userId) {

        return userProfileRepo.findByUserId(userId);
    }

    public ResponseEntity<?> updateProfile(UserProfile userProfile) {
        if (userProfile.getUserId() == securityController.currentUserId()) {
            userProfileRepo.save(userProfile);
            return ResponseEntity.ok("Saved Successful");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");

    }
}
