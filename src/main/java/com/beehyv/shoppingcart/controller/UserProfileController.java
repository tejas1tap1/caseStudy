package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.dto.UserProfileDTO;
import com.beehyv.shoppingcart.entity.UserProfile;

import com.beehyv.shoppingcart.mapper.UserProfileMapper;
import com.beehyv.shoppingcart.services.UserProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserProfileController {
	
	@Autowired
	private UserProfileServices userProfileServices;
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/user-profile/{userId}")
    public UserProfileDTO getProfile(@PathVariable("userId")long userId) {
	
	return UserProfileMapper.INSTANCE.toUserProfileDTO(userProfileServices.getProfile(userId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
   @PutMapping("/user-profile/update")
   public ResponseEntity<?> updateProfile(@RequestBody UserProfileDTO userProfileDTO)
   {
       return userProfileServices.updateProfile(UserProfileMapper.INSTANCE.toUserProfile(userProfileDTO));
   }
}
