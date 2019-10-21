package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.Cart;
import com.beehyv.shoppingcart.entity.UserCredentials;
import com.beehyv.shoppingcart.entity.UserProfile;
import com.beehyv.shoppingcart.repo.CartRepo;
import com.beehyv.shoppingcart.repo.UserCredentialsRepo;
import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserCredentialsServices {
    @Autowired
    private UserCredentialsRepo userCredentialsRepo;
    @Autowired
    private UserProfileRepo userProfileRepo;
    @Autowired
    private CartRepo cartRepo;

//    public ResponseEntity<?> Login(UserCredentials userCredentials) {
//
//        UserCredentials user = userCredentialsRepo.findByEmail(userCredentials.getEmail());
//        if (user == null)
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Doesn't Exist");
//        if (userCredentials.getPassword().equals(user.getPassword())) {
//            return ResponseEntity.ok("Success");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");
//        }
//    }

    public ResponseEntity SignUp(UserCredentials userCredentials) {
        if (userCredentialsRepo.findByEmail(userCredentials.getEmail()) == null) {
            UserProfile userProfile = new UserProfile();
            userProfile.setName(userCredentials.getUserProfile().getName());
            userProfile.setEmail(userCredentials.getEmail());
            userProfileRepo.save(userProfile);
            userCredentials.setUserProfile(userProfile);
            userCredentials.setPassword(new BCryptPasswordEncoder().encode(userCredentials.getPassword()));
            userCredentials.setRole("USER");
            userCredentialsRepo.save(userCredentials);
            Long userId=userCredentialsRepo.findByEmail(userCredentials.getEmail()).getUserProfile().getUserId();
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Failure");
        }
    }
}
