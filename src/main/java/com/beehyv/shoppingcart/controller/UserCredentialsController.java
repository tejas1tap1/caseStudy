package com.beehyv.shoppingcart.controller;


import com.beehyv.shoppingcart.dto.UserCredentialsDTO;
import com.beehyv.shoppingcart.mapper.UserCredentialsMapper;
import com.beehyv.shoppingcart.services.UserCredentialsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserCredentialsController {
    @Autowired
    private UserCredentialsServices userCredentialsServices;

    @PostMapping("/add-user")
    public ResponseEntity<?> SignUp(UserCredentialsDTO userCredentialsDTO) {
        return userCredentialsServices.SignUp(UserCredentialsMapper.INSTANCE.toUserCredentials(userCredentialsDTO));
    }
}
